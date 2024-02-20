# Урок 11. Spring Actuator. Настройка мониторинга с Prometheus и Grafana

## Описание лекции:

- Введение в Spring Boot Actuator
- Использование стандартных конечных точек Actuator для мониторинга
- Настройка и добавление пользовательских конечных точек
- Включение и настройка метрик в Actuator
- Использование Prometheus для сбора метрик
- Интеграция Spring Boot Actuator с Prometheus
- Введение в Grafana и настройка дашбордов
- Визуализация метрик Prometheus с помощью Grafana
- Настройка оповещений и тревог в Grafana
- Лучшие практики мониторинга и анализа метрик

[Директория лекции](https://github.com/MikhailAkulov/Spring_Framework/tree/main/Examples/Example_11/Lecture)

## На семинаре:

1. Бегло про Spring Actuator, Prometheus + Grafana
2. Пишем свой стартер (возможно, в соединении со spring-security)

[Директория семинара](https://github.com/MikhailAkulov/Spring_Framework/tree/main/Examples/Example_11/Seminar)

## Задания:

Проблематика: имеется несколько микросервисов (проектов) на spring-boot: reader-service, book-service, issue-service, ...
Хочется, чтобы в каждом из этих проектов работал аспект-таймер, замеряющий время выполнения метода бина, помеченного аннотацией @Timer (см. дз к уроку 8)

Решение: создать стартер, который будет инкапсулировать в себе аспект и его автоматический импорт в подключающий проект.
То есть:
1. Пишем стартер, в котором задекларирован аспект и его работа
2. Подключаем стартер в reader-service, book-service, issue-service, ...

Шаги реализации:
1. Создаем новый модуль в микросервисном проекте - это и будет наш стартер
2. Берем код с ДЗ-8 (класс аспекта и аннотации) и переносим в стартер
3. В стартере декларируем Configuration и внутри нее декларируем бин - аспект
4. В проекте стартера в resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports прописываем полный путь конфигурации
5. Подключаем зависимость стартера (pom-dependency) в микросервисы
6. Проверяем, что аспект работает

Доп. задание (со звездочкой): придумать точки расширения\конфигурирования аспекта:
Включить\выключить по флажку в конфиге (ConditionalOnProperty)

[Евгений Борисов, Кирилл Толкачев — Boot yourself, Spring is coming (Часть 1)](https://www.youtube.com/watch?v=yy43NOreJG4)

[Директория задания](https://github.com/MikhailAkulov/Spring_Framework/tree/main/Examples/Example_11/Tasks)