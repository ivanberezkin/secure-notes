**Project Assignment: Make an application that can store notes in a Database which user can interact with. 
Focus on security.**

### About Project
Application is using Spring Boot as backend with an RestAPI as endpoints. For security i went with Spring Security.
Backend is following MVC pattern and is connected PostgreSQL database, for database fetching it's using Spring JPA / Hibernate.
Together with Spring Security, BCrypt is used for password encoding to make it more secure, also implemented CSRF protection and Session-cookie authentication (JSESSIONID).
Two roles of user (ADMIN/USER) which gives more permissions to Admin. 
For Frontend the project uses React 19 together with Tailwind CSS to construct the site. 
Currently runs locally, so personal "application.properties" file is needed for database configuration. 

### 🛠 Tech Stack

### Frontend <br>
* **Framework:** React 19 <br>
* **Build Tool:** Vite <br>
* **Language:** TypeScript.<br>
* **Data fetching:** Axios.

### Backend<br>
* **Framework:** Java / Spring Boot<br>
* **Data Access:** Spring Data JPA (Hibernate)
* **Database:** PostgreSQL
* **Architecture:** RESTful API.<br>
* **Server:** Currently only running on localhost.<br>
* **Security:** Spring Security & BCrypt <br>
