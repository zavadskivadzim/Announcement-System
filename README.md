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
**8 - Просмотр списка всех пользователей и их рейтинг**  
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
        "category": "6277b89e-32f0-40da-b4e1-52027613a83a"
    }
-----------------------------------------------------------------------------
**15 - Фильтр объявлений**  
GET:

    localhost:8088/announcements/filter?category=car&maxPrice=200&minPrice=100
-----------------------------------------------------------------------------
**16 - История продаж текущего пользователя**  
GET:

    localhost:8088/announcements_history
-----------------------------------------------------------------------------
**17 - Покупка**

    localhost:8088/buy
PUT:

        "8a528f98-de28-440f-87eb-e281620816cc"
-----------------------------------------------------------------------------

