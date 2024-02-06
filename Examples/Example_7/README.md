# Урок 7. Spring Security. Работа с JWT. Защита от основных видов атак

## Описание лекции:

- Введение в Spring Security
- Понимание аутентификации и авторизации
- Конфигурация Spring Security
- Работа с пользовательскими данными и ролями
- Использование JWT для безопасной аутентификации
- Защита от основных видов атак: CSRF, XSS, SQL Injection
- Сессии и управление состоянием в Spring Security
- Обработка ошибок и исключений в Spring Security
- Тестирование безопасности приложения
- Лучшие практики использования Spring Security

[Директория лекции](https://github.com/MikhailAkulov/Spring_Framework/tree/main/Examples/Example_7/Lecture/)

## На семинаре:

1. Что такое spring-security и зачем оно нужно
2. Общие компоненты: SecurityContext, Authorization, Authority, UserDetails и тд
3. Настройка basic-авторизации с хранением в оперативной памяти
4. Настройка basic-авторизации с хранением в БД
5. Настройка bearer-авторизации
6. Поднятие внешнего auth-сервера (Keycloak) и настройка авторизации по протоколу OAuth2

[Директория семинара](https://github.com/MikhailAkulov/Spring_Framework/tree/main/Examples/Example_7/Seminar)

## Задания:

1. Для ресурсов, возвращающих HTML-страницы, реализовать авторизацию через login-форму.
   
   Остальные /api ресурсы, возвращающие JSON, закрывать не нужно!
2. 
   2.1.* Реализовать таблицы User(id, name, password) и Role(id, name), связанные многие ко многим

   2.2.* Реализовать UserDetailsService, который предоставляет данные из БД (таблицы User и Role)
3. 
   3.3* Ресурсы выдач (issue) доступны обладателям роли admin
      
   3.4* Ресурсы читателей (reader) доступны всем обладателям роли reader
      
   3.5* Ресурсы книг (books) доступны всем авторизованным пользователям

[Директория задания](https://github.com/MikhailAkulov/Spring_Framework/tree/main/Examples/Example_7/Tasks)