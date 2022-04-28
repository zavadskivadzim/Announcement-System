# Announcement-System
**1 - User registration**  
The first user to register gets the "ROLE_ADMIN", all the rest - "ROLE_USER"  

    localhost:8088/register
 POST:

    {
        "login": "admin",
        "password": "admin"
    }
-----------------------------------------------------------------------------
**2 - User authorization**

    localhost:8088/auth
POST:

    {
        "login": "admin",
        "password": "admin"
    }
-----------------------------------------------------------------------------
**3 - update User**

    localhost:8088/users
PUT:

    {
        "id": "",
        "firstName": "",
        "surname": "",
        "birthday": "xxxx-xx-xx",
        "email": ""
    }
-----------------------------------------------------------------------------
**create Announcement**

    localhost:8088/announcements
POST:

    {
        "body": "dark car",
        "price": "190",
        "category": "6277b89e-32f0-40da-b4e1-52027613a83a"
    }
-----------------------------------------------------------------------------
**create Category**

    localhost:8088/categories

    {
        "name": "motorbike"
    }

**update Category**  
  
    localhost:8088/admin/categories  
PUT:

    {
        "id": "06a939bf-f72b-48b4-a510-74d5e2fd9c7e",
        "name": "bike"
    }

-----------------------------------------------------------------------------
**find All Comments**

    localhost:8088/admin/comments

**create Comment**

    localhost:8088/comments
POST:

    {
        "body": "comment",
        "announcement": "6ad5b920-1c36-4336-bc3a-17e020c17823"
    }