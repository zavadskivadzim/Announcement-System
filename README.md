# Announcement-System
Run terminal command in project directory:

        mvn clean install

### Start Rest application
To start Rest server (rest-app module):

    java -jar ./rest-app/target/rest-app-1.0-SNAPSHOT.jar

-----------------------------------------------------------------------------
**1 - Регистрация пользователя**   
Первому зарегистрировавшемуся пользователь по умолчанию присваивается роль администратора,  
все последующим - обычный пользователь
    
    localhost:8088/register

POST:

    {
        "login": "admin",
        "password": "admin"
    }

-----------------------------------------------------------------------------
**2 - Авторизация пользователя**

    localhost:8088/auth
POST:

    {
        "login": "admin",
        "password": "admin"
    }
-----------------------------------------------------------------------------
**3 - Редактирование профиля**  

    localhost:8088/users
PUT:

    {
        "firstName": "",
        "surname": "",
        "birthday": "xxxx-xx-xx",
        "email": ""
    }
-----------------------------------------------------------------------------
**4 - Просмотр списка всех пользователей**  
GET:

    localhost:8088/admin/users
-----------------------------------------------------------------------------
**5 - Найти пользователя по id**  
GET:

    localhost:8088/users/{id}
-----------------------------------------------------------------------------
**6 - Удаление пользователя по id**  
DELETE:

    localhost:8088/admin/users/{id}
-----------------------------------------------------------------------------
**7 - Просмотр списка всех ролей**  
GET:

    localhost:8088/admin/roles
-----------------------------------------------------------------------------
**8 - Список всех пользователей и их рейтинг**  
GET:

    localhost:8088/admin/users_rating
-----------------------------------------------------------------------------
**9 - Просмотр списка категорий товаров**  
GET:

    localhost:8088/categories
-----------------------------------------------------------------------------
**10 - Найти категорию товаров по id**  
GET:

    localhost:8088/categories/{id}
-----------------------------------------------------------------------------
**11 - Создание новой категории товаров**

    localhost:8088/admin/categories
POST:

    {
        "name": "boat"
    }
-----------------------------------------------------------------------------
**12 - Редактирование названия категории товаров**

    localhost:8088/admin/categories
PUT:

    {
        "id": "",
        "name": "bike"
    }
-----------------------------------------------------------------------------
**13 - Удаление категории товаров по id**  
DELETE:

    localhost:8088/admin/categories/{id}
-----------------------------------------------------------------------------
**14 - Создание обьявления**

    localhost:8088/announcements
POST:

    {
        "body": "dark car",
        "price": "190",
        "category": "{id}"
    }
-----------------------------------------------------------------------------
**15 - Фильтр объявлений**  
GET:

    localhost:8088/announcements/filter?category=car&maxPrice=200&minPrice=100
-----------------------------------------------------------------------------
**16 - Просмотр списка всех объявлений (в т.ч. неактивных)**  
GET:

    localhost:8088/admin/announcements
-----------------------------------------------------------------------------
**17 - Удаление обьявления по id**  
DELETE:

    localhost:8088/announcements/{id}
-----------------------------------------------------------------------------
**18 - Список объявлений текущего пользователя**  
GET:

    localhost:8088//announcements/mine
-----------------------------------------------------------------------------
**19 - История продаж текущего пользователя**  
GET:

    localhost:8088/announcements_history
-----------------------------------------------------------------------------
**20 - Покупка товара**

    localhost:8088/buy
PUT:

        "{id}"
-----------------------------------------------------------------------------
**21 - Просмотр списка всех комментариев**  
GET:

    localhost:8088/admin/comments
-----------------------------------------------------------------------------
**22 - Создание комментария**

    localhost:8088/comments
POST:

    {
        "body": "comment",
        "announcement": "{id}"
    }