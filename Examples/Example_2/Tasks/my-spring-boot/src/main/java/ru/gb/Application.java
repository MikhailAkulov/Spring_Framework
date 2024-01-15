package ru.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);

//		StudentRepository studentRepository = (StudentRepository) context.getBean("myStudentRepositoryBean");
//		StudentRepository studentRepository11 = context.getBean("myStudentRepositoryBean", StudentRepository.class);

//		StudentRepository studentRepository = (StudentRepository) context.getBean("studentRepository");

//		StudentRepository studentRepository = (StudentRepository) context.getBean("myStudentRepository");

//		StudentRepository studentRepository1 = context.getBean(StudentRepository.class);
//		StudentRepository studentRepository2 = context.getBean(StudentRepository.class);
//		StudentRepository studentRepository3 = context.getBean(StudentRepository.class);

//		System.out.println(studentRepository == studentRepository1);

//		System.out.println(studentRepository1 == studentRepository2);
//		System.out.println(studentRepository1 == studentRepository3);
//		System.out.println(studentRepository2 == studentRepository3);

//		StudentRepository studentRepository = new StudentRepository();
//		System.out.println(studentRepository.getAll());
//		System.out.println(studentRepository.getById(2));
	}

}
