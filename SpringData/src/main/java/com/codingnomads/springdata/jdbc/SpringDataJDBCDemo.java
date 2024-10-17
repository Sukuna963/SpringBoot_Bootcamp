package com.codingnomads.springdata.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringDataJDBCDemo implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJDBCDemo.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            jdbcTemplate.execute("CREATE TABLE employees (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "first_name VARCHAR(255) NOT NULL," +
                    "last_name  VARCHAR(255) NOT NULL);");
        } catch (Exception e) {

        }

        List<Object[]> splitUpNames = Stream.of("Java Ninja",
                                                "Spring Guru",
                                                "Java Guru",
                                                "Spring Ninja")
                                            .map(name -> name.split(" "))
                                            .collect(Collectors.toList());

        for(Object[] name : splitUpNames) {
            jdbcTemplate.execute(String.format("INSERT INTO employees" +
                    "(first_name, last_name) " +
                    "VALUES ('%s','%s')", name[0],name[1]));
        }

        jdbcTemplate.query("SELECT id, first_name, last_name " +
                "FROM employees " +
                "WHERE first_name = 'Java'",
                (rs, rowNum) -> new Employee(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                )).forEach(employee -> System.out.println(employee.toString()));

        jdbcTemplate.execute("TRUNCATE TABLE employees;");
        jdbcTemplate.execute("DROP TABLE employees");
    }
}
