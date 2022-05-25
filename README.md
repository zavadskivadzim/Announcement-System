# Announcement-System
Run terminal command in project directory:  
``
mvn clean install
``
### Start Rest application
To start Rest server (rest-app module):  
``
java -jar ./rest-app/target/rest-app-1.0-SNAPSHOT.jar
``
-----------------------------------------------------------------------------
##Postman  
[https://web.postman.co/workspace/d58acace-cf85-45bf-84b2-383a5bb430e7/collection/18754865-ea1c06a8-e209-492f-b370-58faabcb68a2?action=share&creator=18754865
](https://web.postman.co/workspace/d58acace-cf85-45bf-84b2-383a5bb430e7/collection/18754865-ea1c06a8-e209-492f-b370-58faabcb68a2?action=share&creator=18754865)

**1.1 - Регистрация пользователя**   
Первому зарегистрировавшемуся пользователь по умолчанию присваивается роль администратора,  
все последующим - обычный пользователь
    
    localhost:8088/register

POST:

    {
        "login": "admin",
        "password": "admin"
    }

-----------------------------------------------------------------------------
**1.2 - Авторизация пользователя**

    localhost:8088/auth
POST:

    {
        "login": "admin",
        "password": "admin"
    }
-----------------------------------------------------------------------------
**2.1 - Просмотр списка всех ролей**  
GET:

    localhost:8088/admin/roles
-----------------------------------------------------------------------------
**2.2 - Просмотр списка всех пользователей**  
GET:

    localhost:8088/admin/users
-----------------------------------------------------------------------------
**2.3 - Просмотр профиля текущего пользователя**  
GET:

    localhost:8088/users/current
-----------------------------------------------------------------------------
**2.4 - Редактирование профиля текущего пользователя**  

    localhost:8088/users
PUT:

    {
        "firstName": "",
        "surname": "",
        "birthday": "xxxx-xx-xx",
        "email": ""
    }
-----------------------------------------------------------------------------
**2.5 - Удаление пользователя по id**  
DELETE:

    localhost:8088/admin/users/{id}
-----------------------------------------------------------------------------
**2.6 - Список всех пользователей и их рейтинг**  
GET:

    localhost:8088/admin/users_rating
-----------------------------------------------------------------------------
**3.1 - Просмотр списка категорий товаров**  
GET:

    localhost:8088/categories
-----------------------------------------------------------------------------
**3.2 - Создание новой категории товаров**

    localhost:8088/admin/categories
POST:

    {
        "name": "boat"
    }
-----------------------------------------------------------------------------
**3.3 - Редактирование категории товаров**

    localhost:8088/admin/categories
PUT:

    {
        "id": "",
        "name": "bike"
    }
-----------------------------------------------------------------------------
**3.4 - Удаление категории товаров по id**  
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

    localhost:8088/announcements/mine
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
-----------------------------------------------------------------------------
**23 - Изменение комментария**

    localhost:8088/comments
PUT:

    {
        "id": "{id}",
        "body": "comment",
        "announcement": "{id}"
    }
-----------------------------------------------------------------------------
**24 - Просмотр комментариев к объявлению**
GET:

    localhost:8088/comments_to_announcement/{id}
-----------------------------------------------------------------------------
**25 - Проплатить объявление**

    localhost:8088/payments
POST:

    {
        "announcementId": "{id}",
        "duration": "5"
    }
-----------------------------------------------------------------------------
**26 - Просмотр списка всех оплат за объявления**  
GET:

    localhost:8088/admin/payments
-----------------------------------------------------------------------------
**27 - Выставить оценку пользователю**

    localhost:8088/grades
POST:

    {
        "receiverId": "f3705439-804c-456c-8d84-f959b4f1b79b",
        "grade": "7"
    }
-----------------------------------------------------------------------------
**28 - Изменение оценки**

    localhost:8088/grades
PUT:

    {
        "id": "{id}",
        "grade": "8"
    }
-----------------------------------------------------------------------------
**29 - Просмотр списка всех оценок**

    localhost:8088/admin/grades

-----------------------------------------------------------------------------
**30 - Отправить сообщение**

    localhost:8088/messages
POST:

    {
        "receiverId": "{id}",
        "body": "Hello world!!!"
    }
-----------------------------------------------------------------------------
**31 - Изменить сообщение**

    localhost:8088/messages
PUT:

    {
        "id": "{id}",
        "body": "Hello world!!!"
    }
-----------------------------------------------------------------------------
