# Announcement-System
Run terminal command in project directory:

        mvn clean install

### Start Rest application
To start Rest server (rest-app module):

    java -jar ./rest-app/target/rest-app-1.0-SNAPSHOT.jar

-----------------------------------------------------------------------------
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

**update User**

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