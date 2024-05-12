# Registry Service

Реестр районов и фермеров на SpringBoot

# Использованные технологии

* [Spring Boot](https://spring.io/projects/spring-boot) – как основной фрэймворк
* [PostgreSQL](https://www.postgresql.org/) – как основная реляционная база данных

# База данных

* База поднимается в docker с помощью файлов [run.sh](/run.sh) в корне проекта
* Liquibase сам накатывает нужные миграции на голый PostgreSql при старте приложения
* В коде продемонстрирована работа с JPA (Hibernate)

# Как начать разработку?

1. Сначала нужно склонировать этот репозиторий

```shell
git clone https://github.com/gutorov/registry.git
```

3. Далее нужно создать совершенно пустой репозиторий в github/gitlab

4. Создаём новый репозиторий локально и коммитим изменения

```shell
git init
git remote add origin <link_to_repo>
git add .
git commit -m "<msg>"
```

Готово, можно начинать работу!

# Как запустить локально?

Сначала нужно развернуть базу данных [run.sh](../run.sh)

Далее собрать maven проект

```shell
# Нужно запустить из корневой директории, где лежит build.gradle.kts
maven install
```

Запустить jar'ник

```shell
java -jar target/registry-1.0-SNAPSHOT.jar.original
```

Но легче всё это делать через IDE

# Код

RESTful приложения реестр с endpoint'ами, которые отвечают за добавление, обновление, получениие и удаления (архивации) сущностей из реестра

Для полной и интерактивной документации всех API-эндпоинтов, пожалуйста, посетите наш Swagger UI:
Документация [API Swagger](https://localhost:8081/swagger-ui.html)
(Доступна после запуска прилодения локально)

* Обычная трёхслойная
  архитектура – [Controller](src/main/java/respak/registry_service/controller), [Service](src/main/java/respak/registry_service/service), [Repository](src/main/java/respak/registry_service/repository)
* Слой Repository реализован на JPA (Hibernate)
* Написан [GlobalExceptionHandler](src/main/java/respak/registry_service/handler/GlobalExceptionHandler.java)
  который умеет возвращать ошибки в формате `{"code":"CODE", "message": "message"}`
    
  