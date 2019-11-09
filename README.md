# Student-Management-System-API
Student Management System API using Java Servlet

Javadoc is avaliable at [Javadoc](https://projects.computopedia.com/student_management_system/)<br>
API doc is available at [API Doc](https://documenter.getpostman.com/view/3694598/SVtVU7zU)

**Instruction:**
- In order to run this project please download/clone it to your local machine.
- Search for **base** package.
- Find **Controller.java** file.
- Find **line 31** : *public static String USER_UPLOAD_DIR = "D";*
- Now change the ending "D" to your computers local partition name whare you want to store images and other files.
- After changing go to the exact pratition and create the following file structure
```
    --D
      |--project_5th_sem
         |--uploads
```
- Create a new Database and install the Database and import the db-setup.sql file
- In the **base** package, find **Model.java** file
- Find **line 37** : *Database.config("\<sql-server-url\>", "\<username\>", "\<password\>", "\<database-name\>");*
- Change this line according to your local set up.
- Done. Your are good to go.
  



