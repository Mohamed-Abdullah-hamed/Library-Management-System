## 1. Project Overview
Project Name: Library Management System API
Description: A RESTful API for managing books, patrons, and borrowing records in a library.
## 2. Prerequisites
Java Development Kit (JDK): Ensure JDK 17 is installed.
Maven: Ensure Maven is installed for managing dependencies and building the project.
Database: ensure mysql server is installed and running 

## 3. Setting Up the Project
# 3.1. Cloning the Repository
   git clone <repository-url>
   cd library-management-system
# 3.2. Configuring the Database
   assuming there is user name called libarian and its password is Libarian_123@ on your database or you can change them in application.properties file after cloning the project
   as the following 
   spring.datasource.username=[write here your database userName ]
   spring.datasource.password=[write here your database password ]
## 3.3. Running the Application
   Use Maven to build and run the application:
   open cmd and navigate to the directory of project on your machine
   then write these commands
   mvn clean install
   mvn spring-boot:run

## 4. API Endpoints
with all GET API Endpoints I secure them to run with the role USER or role ADMIN so you should use HTTP Basic Authentication with
USER ROLE CREDENTIALS{ userName  Mohamed and password  mohamed123 }
or with ADMIN ROLE CREDENTIALS { userName  Libarian and password  libarian123}
with all POST or PUT  API Endpoints I secure them to run with role ADMIN only so you should use HTTP Basic Authentication with ADMIN ROLE CREDENTIALS { userName  Libarian and password  libarian123}

# 4.1. Book Management Endpoints
  there are some constraints on the body of the book 
  {
    "title": its length should be between 4 and 50 ,
    "author":"its length should be between 4 and 20 ,
    "isbn": should be valid 13 isbn as "978-3-16-148410-0",
    "publicationYear":sholud be equal or before the current year
  }
  Retrieve All Books: GET /api/books
  Retrieve Book by ID: GET /api/books/{id}
  Add a New Book: POST /api/books
  Update Book Information: PUT /api/books/{id}
  Delete a Book: DELETE /api/books/{id}
  
# 4.2. Patron Management Endpoints
  there are some constraints on the body of the Patron 
  {
   "name":its length should be between 4 and 20 ,
    "identityNumber": should be a valid identity numer of UAE as "784-1244-1234567-3",
    "phoneNumber": should be a valid mobile number of UAE as "501334563"
  }
  Retrieve All Patrons: GET /api/patrons
  Retrieve Patron by ID: GET /api/patrons/{id}
  Add a New Patron: POST /api/patrons
  Update Patron Information: PUT /api/patrons/{id}
  Delete a Patron: DELETE /api/patrons/{id}
# 4.3. Borrowing Endpoints
   there are some constraints on borrowing a book 
   as this book must be available now in the library and the patron does not have another book
  Borrow a Book: POST /api/borrow/{bookId}/patron/{patronId}
  Return a Book: PUT /api/return/{bookId}/patron/{patronId}











   
