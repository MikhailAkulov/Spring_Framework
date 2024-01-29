# Урок 5. Spring Data для работы с базами данных

## Описание лекции:

- Введение в Spring Data
- Работа с JPA и Hibernate в Spring Data
- Создание репозиториев и работа с CRUD операциями
- Создание и использование пользовательских запросов
- Работа со связанными сущностями
- Работа с транзакциями в Spring Data
- Понимание и использование Lazy и Eager загрузки
- Оптимизация производительности и кеширование
- Обработка исключений в Spring Data
- Тестирование Spring Data приложений

[Директория лекции](https://github.com/MikhailAkulov/Spring_Framework/tree/main/Examples/Example_5/Lecture/)

## На семинаре:

- spring-data-jdbc (бин datasource, jdbcTemplate)
- spring-data-jpa (jpa-провайдер, query methods, query, query-executor)
- spring-data-rest (если останется время)

[Директория семинара](https://github.com/MikhailAkulov/Spring_Framework/tree/main/Examples/Example_5/Seminar)

## Задания:

1. Подключить базу данных к проекту "библиотека", который разрабатывали на прошлых уроках.
   * 1.1 Подключить spring-boot-starter-data-jpa (и необходимый драйвер) и указать параметры соединения в application.yml
   * 1.2 Для книги, читателя и факта выдачи описать JPA-сущности
   * 1.3 Заменить самописные репозитории на JPA-репозитории
   * Базу данных можно использовать любую (h2, mysql, postgres).

[Директория задания](https://github.com/MikhailAkulov/Spring_Framework/tree/main/Examples/Example_5/Tasks)