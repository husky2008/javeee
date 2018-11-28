package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootdemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootdemoApplication.class, args);


		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ConfigurationDemo.class);

		/*Student o = (Student)annotationConfigApplicationContext.getBean(Student.class);
		System.out.println(o.getName());*/
		ConfigurationDemo o1 = annotationConfigApplicationContext.getBean(ConfigurationDemo.class);

		Object o2 = annotationConfigApplicationContext.getBean(ImportDemo.class);
		System.out.println(o2);

	}
}
