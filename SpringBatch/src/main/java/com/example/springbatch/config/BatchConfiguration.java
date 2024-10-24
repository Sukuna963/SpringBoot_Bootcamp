package com.example.springbatch.config;

import com.example.springbatch.models.Designation;
import com.example.springbatch.models.Employee;
import com.example.springbatch.repositories.EmployeeRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Date;
import java.util.Map;

@Configuration
public class BatchConfiguration {

    @Bean
    public Job job(JobRepository jobRepository, Step nameStep, Step designationStep) {

        return new JobBuilder("employee-loader-job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(nameStep)
                .next(designationStep)
                .build();
    }

    @Bean
    public Step nameStep(JobRepository jobRepository,
                         ItemReader<Employee> csvReader,
                         NameProcessor processor,
                         EmployeeWriter writer,
                         PlatformTransactionManager transactionManager) {

        return new StepBuilder("name-step", jobRepository)
                .<Employee, Employee>chunk(100, transactionManager)
                .reader(csvReader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step designationStep(JobRepository jobRepository,
                                ItemReader<Employee> repositoryReader,
                                DesignationProcessor processor,
                                EmployeeWriter writer,
                                PlatformTransactionManager transactionManager) {

        return new StepBuilder("designation-step", jobRepository)
                .<Employee, Employee>chunk(100, transactionManager)
                .reader(repositoryReader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public FlatFileItemReader<Employee> csvReader(@Value("${inputFile}") String inputFile) {

        return new FlatFileItemReaderBuilder<Employee>()
                .name("csv-reader")
                .resource(new ClassPathResource(inputFile))
                .delimited()
                .names("id", "name", "designation")
                .linesToSkip(1)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){{setTargetType(Employee.class);}})
                .build();
    }

    @Bean
    public RepositoryItemReader<Employee> repositoryReader(EmployeeRepository employeeRepository) {

        return new RepositoryItemReaderBuilder<Employee>()
                .repository(employeeRepository)
                .methodName("findAll")
                .sorts(Map.of("id", Sort.Direction.ASC))
                .name("repository-reader")
                .build();
    }

    @Component
    public static class NameProcessor implements ItemProcessor<Employee, Employee> {

        @Override
        public Employee process(Employee employee) {
            employee.setName(employee.getName().toUpperCase());
            employee.setNameUpdateAt(new Date());

            return employee;
        }
    }

    @Component
    public static class DesignationProcessor implements ItemProcessor<Employee, Employee> {

        @Override
        public Employee process(Employee employee)  {
            employee.setDesignation(Designation.getByCode(employee.getDesignation()).getTitle());
            employee.setDesignationUpdateAt(new Date());

            return employee;
        }
    }

    @Component
    public static class EmployeeWriter implements ItemWriter<Employee> {

        @Autowired
        private EmployeeRepository employeeRepository;

        @Value("${sleepTime}")
        private Integer SLEEP_TIME;

        @Override
        public void write(@NonNull Chunk<? extends Employee> employees) throws InterruptedException{
            employeeRepository.saveAll(employees);
            Thread.sleep(SLEEP_TIME);

            System.out.println("Save Employee: " + employees);
        }
    }
}
