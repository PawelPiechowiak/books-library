Book Library Application

1. Short description of the application</br></br>
The application I created is used to read a JSON file with many books and to process them. It has 3 main functions: finding a book by ISBN, finding all books by category and show list of all authors and their rating in descending order.

2. Short description of the application framework</br></br>
As the application framework I chose Spring Boot because it is simple to understand and easy to implementation.

3. Short description of the testing framework</br></br>
As a testing framework to test methods in the application I chose JUnit4 because I've worked with this framework before. For testing endpoints I chose RestAssured as recommended.

4. Design patterns</br></br>
I used one design pattern: Builder. I used this because it is recommended to use when our class has many fields like the book class. The code is more clean when object is creating.

5. How to build and run project</br></br>
To build the project use following command: `gradle clean build`</br>
After building the application run following comannd to start it: `gradle bootRun`

6. Api Documentation </br></br>
Url for finding book by ISBN: http://localhost:8080/books/{isbn} - in this case program returns api according to to the table contained in "Examples and additional notes.pdf"</br></br>
Url for finding books by category: http://localhost:8080/books/categories/{category} - in this case program returns api according to to the table contained in "Examples and additional notes.pdf"</br></br>
Url for list authors with their rating: http://localhost:8080/authors - in this case program returns list with object which has 2 fields: String name oraz Double averageRating.
