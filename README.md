**Описание**:
Этот проект является примером веб-приложения, разработанного с использованием Spring Boot, Spring Security, Spring Data JPA и Thymeleaf.
В приложении реализована функциональность CRUD-операций для управления пользователями, а также настроена аутентификация и авторизация с поддержкой нескольких ролей.
Пользователи с ролью ADMIN могут выполнять операции создания, редактирования и удаления пользователей, а пользователи с ролью USER имеют доступ только к своей личной странице.

**Функциональность**:
• Аутентификация и авторизация:

Настроена Spring Security с кастомным обработчиком успешной аутентификации, который перенаправляет пользователя в зависимости от его ролей.
• CRUD-операции:

Администратор может создавать, редактировать и удалять пользователей через веб-интерфейс.
• Просмотр профиля:

Пользователь может просматривать свой профиль.
• Инициализация данных:

При запуске приложения (через CommandLineRunner) автоматически создаются базовые роли (ROLE_ADMIN и ROLE_USER) и две учётные записи:
admin / adminpass
user / userpass
Используемые технологии:
• Java 21 (или совместимая версия) • Spring Boot 2.7.5 • Spring Security • Spring Data JPA • Thymeleaf • MySQL • Maven

**Установка и запуск:**
Предварительные требования:

Установленный JDK (рекомендуется JDK 21 или совместимая версия)
Maven
MySQL (создайте базу данных, например, с именем "kata")

**Git**
Клонирование репозитория: В терминале выполните: git clone https://github.com/yourusername/yourrepository.git cd yourrepository

Настройка базы данных: Откройте файл src/main/resources/application.properties и настройте параметры подключения к базе данных:

spring.datasource.url=jdbc:mysql://localhost:3306/kata?serverTimezone=UTC spring.datasource.username=YOUR_DB_USERNAME spring.datasource.password=YOUR_DB_PASSWORD spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

server.servlet.context-path=/

Сборка проекта: В корневой папке проекта выполните: mvn clean install

**Запуск приложения:**

Вариант 1. Запуск через встроенный сервер (Maven): mvn spring-boot:run

Вариант 2. Запуск исполняемого WAR файла: java -jar target/ROOT.war

**Доступ к приложению:**

Страница логина: http://localhost:8080/login
Учётные записи: • admin / adminpass – после входа перенаправляется на http://localhost:8080/admin • user / userpass – после входа перенаправляется на http://localhost:8080/user
Использование:
• Администратор: На странице http://localhost:8080/admin администратор видит список всех пользователей и имеет доступ к функциям создания, редактирования и удаления пользователей.

• Обычный пользователь: На странице http://localhost:8080/user пользователь видит свой профиль.

**Тестирование:**
Для запуска тестов выполните: mvn test
