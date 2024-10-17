package com.example.ioc;

import com.example.ioc.dp.GreetingRenderer;
import com.example.ioc.dp.IoCDemoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class IocApplication {

	public static void main(String[] args) {
		SpringApplication.run(IocApplication.class, args);
		ApplicationContext ctx = new AnnotationConfigApplicationContext(IoCDemoConfiguration.class);
		GreetingRenderer greetingRenderer = ctx.getBean("renderer", GreetingRenderer.class);
		greetingRenderer.render();
	}

}
