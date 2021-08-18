package ru.businessculture;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.businessculture.starter.Starter;

@SpringBootApplication
public class MultiThreadTaskApplication {

	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext();
		context.scan("ru.businessculture");
		context.refresh();

		var starter = (Starter) context.getBean("starter");

		starter.startThreads();
	}
}
