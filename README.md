# Дипломный проект по теме Тестирование API на курсе "Автоматизатор тестирования на Java"

### Технологии проекта:

Java 11, JUnit4, Maven, Allure
___________________________________________________________________________________
# Задание

### Создание пользователя:
- создать уникального пользователя;
- создать пользователя, который уже зарегистрирован;
- создать пользователя и не заполнить одно из обязательных полей.

### Логин пользователя:
- логин под существующим пользователем,
- логин с неверным логином и паролем.

### Изменение данных пользователя:
- с авторизацией,
- без авторизации,

Для обеих ситуаций нужно проверить, что любое поле можно изменить. Для неавторизованного пользователя — ещё и то, что система вернёт ошибку.

### Создание заказа:
- с авторизацией,
- без авторизации,
- с ингредиентами,
- без ингредиентов,
- с неверным хешем ингредиентов.

### Получение заказов конкретного пользователя:
- авторизованный пользователь,
- неавторизованный пользователь.
