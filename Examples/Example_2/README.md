# Урок 2. Основы Spring. Spring Boot

## Описание лекции:

- Введение в Spring Framework
- Инверсия управления (IoC) и внедрение зависимостей (DI)
- Создание и конфигурация Spring Bean'ов
- Аннотации в Spring: @Component, @Autowired и другие
- Введение в Spring Boot и его преимущества
- Создание Spring Boot приложения
- Настройка Spring Boot приложения через application.properties/yml
- Работа с профилями в Spring Boot
- Понимание Spring Boot Autoconfiguration
- Создание простого веб-сервиса с помощью Spring Boot

[Директория лекции](https://github.com/MikhailAkulov/Spring_Framework/tree/main/Examples/Example_2/Lecture/)

## На семинаре:

- Создание простого Spring Boot приложения.
- Настройка и использование Spring Dependency Injection.
- Создание простого REST контроллера.

[Директория семинара](https://github.com/MikhailAkulov/Spring_Framework/tree/main/Examples/Example_2/Seminar)

## Задания:

1. Создать spring-boot приложение с [помощью](https://start.spring.io/)
2. Создать Класс Student c полями: идентификатор, имя, имя группы
3. Создать контроллер, обрабатывающий входящие запросы:
   * 3.1. GET /student/{id} - получить студента по ID
   * 3.2. GET /student - получить всех студентов
   * 3.3. GET /student/search?name='studentName' - получить список студентов, чье имя содержит подстроку studentName
   * 3.4. GET /group/{groupName}/student - получить всех студентов группы
   * 3.5. POST /student - создать студента (принимает JSON) (отладиться можно с помощью Postman)
   * 3.6. DELETE /student/{id} - удалить студента
4. При старте приложения, в программе должно быть создано 5-10 студентов.

[Директория задания](https://github.com/MikhailAkulov/Spring_Framework/tree/main/Examples/Example_2/Tasks)