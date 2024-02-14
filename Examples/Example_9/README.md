# Урок 9. Spring Cloud. Микросервисная архитектура

## Описание лекции:

- Введение в микросервисную архитектуру
- Разработка микросервисов с помощью Spring Boot
- Использование Spring Cloud для облегчения разработки микросервисов
- Регистрация и обнаружение сервисов с помощью Eureka
- Реализация клиентского балансировщика с помощью Ribbon
- Обработка сбоев и обеспечение отказоустойчивости с помощью Hystrix
- Роутинг и фильтрация запросов с помощью Zuul
- Обеспечение безопасности микросервисов с помощью Spring Security
- Масштабирование и развертывание микросервисов
- Лучшие практики в микросервисной архитектуре

## На семинаре:

1. Spring Cloud - что это и зачем нужно
2. DiscoveryService (Eureka)
3. EurekaClient, Application, Instance
4. Взаимодействие через WebClient
5. Теория про Feign

Полезное про [Алгоритмы балансировки нагрузок](https://habr.com/ru/companies/ruvds/articles/732648/)

[Директория семинара](https://github.com/MikhailAkulov/Spring_Framework/tree/main/Examples/Example_9/Seminar)

## Задания:

1. Восстановить пример, рассмотренный на уроке (запустить эврику и 2 сервиса; заставить их взаимодействовать)

   Сдать скриншот страницы /eureka/apps с зарегистрированными приложениями.

   На скрине должно быть видно оба сервиса (book-service, issuer-service)

2. co * Добавить третий сервис: сервис читателей.
   ```json
   [
      {
         "id": "733a8a9f-7fbf-4eb6-9900-f3338007d848",
         "issuedAt": "2024-11-28",
         "book": {
            "id": "78a0d4d5-67db-45f8-b846-da410f01aa11",
            "name": "Absalom, Absalom!",
            "author": {
               "id": "4deeeb5b-f263-4c5f-9c8c-62b83b0977ee",
               "firstName": "Justen",
               "lastName": "Huels"
            }
         },
         "reader": {
            "id": "78a0d4d5-67db-45f8-b846-da410f01bc34",
            "firstName": "Имя читателя",
            "lastName": "Фамилия читателя"
         }
      }
   ]
   ```

[Директория задания](https://github.com/MikhailAkulov/Spring_Framework/tree/main/Examples/Example_9/Tasks)