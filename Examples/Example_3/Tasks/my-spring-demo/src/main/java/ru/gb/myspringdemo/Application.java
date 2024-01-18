package ru.gb.myspringdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * POST: http://localhost:8080/issue
	 * Body:
	 * {
	 *     "readerId": 1,
	 *     "bookId": 2
	 * }
	 *
	 * Получен запрос на выдачу: readerId = 1, bookId = 2
	 * {
	 *     "id": 1,
	 *     "bookId": 2,
	 *     "readerId": 1,
	 *     "timestamp": "2024-01-18T22:56:52.3333461"
	 * }
	 */
}
