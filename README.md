# Real-Time Event Ticketing System

## Introduction
The Real-Time Event Ticketing System is a full-stack application built with **Spring Boot** for the backend and **Angular** for the frontend. It enables efficient management of event tickets, secure user authentication, and real-time updates using **WebSockets**. The system supports roles for Admin, Vendor, and Customers.

---

## Features
- **User Authentication**: Secure registration and login for users and admins.
- **Ticket Management**: Vendors can release tickets, and customers can purchase tickets.
- **Real-Time Updates**: WebSocket connections provide live ticket availability updates.
- **Admin Configuration**: Admins can configure ticket release rates and other settings.
- **Swagger API Documentation**: Access detailed API documentation.

---

## Prerequisites
Before running the application, ensure the following tools are installed:

- **Java Development Kit (JDK)**: Version 17 or later.
- **Maven**: Version 3.8.0 or later.
- **SpringBoot**: Version 
- **Node.js and npm**:
  - Node.js: Version 14.x or later.
  - npm: Version 6.x or later.
- **MongoDB**: Running on cloud or locally on `localhost:27017`.
- **Web Browser**: Google Chrome or Firefox recommended.

---

## Setup Instructions

### Backend Setup (Spring Boot Application)
1. **Navigate to the Backend Directory**:
   ```bash
   cd Server
   ```

2. **Build the Backend Project**:
   Use Maven to clean and build the project:
   ```bash
   mvn clean install
   ```

3. **Configure MongoDB Connection**:
   Update `application.properties` with your MongoDB settings (if required):
   Local MongoDB repository:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/ticketing-system
   ```
   Cloud MongoDB repository:
   ```properties
   spring.data.mongodb.uri=mongodb+srv://<your-username>:<your-password>@<cluster-name>.mongodb.net/<your-database>?retryWrites=true&w=majority

   ```

5. **Run the Backend Server**:
   ```bash
   mvn spring-boot:run
   ```
   The backend will start on **http://localhost:8080**.

### Frontend Setup (Angular Application)
1. **Navigate to the Frontend Directory**:
   ```bash
   cd CLIENT
   ```

2. **Install Frontend Dependencies**:
   ```bash
   npm install
   ```

3. **Start the Frontend Development Server**:
   ```bash
   ng serve
   ```
   The frontend will run on **http://localhost:4200**.

---

## Usage Instructions

### Starting the System
- **Backend**: Ensure the Spring Boot server is running on port `8181`.
- **Frontend**: Ensure the Angular app is running on port `4200`.

### Access the Application
Open your browser and visit:
```
http://localhost:4200
```

### Key Functionalities
1. **User Authentication**:
   - Register a new account via the "Sign Up" page.
   - Log in with your credentials via the "Login" page.

2. **Ticket Management**:
   - Vendors can release tickets for events.
   - Customers can view and purchase available tickets.
   - Real-time updates are displayed as tickets are sold.

3. **Admin Configurations**:
   - Admins can adjust ticket release configurations via the admin dashboard.

4. **Swagger API Documentation**:
   Access the API documentation at:
   ```
   http://localhost:8080/swagger-ui.html
   ```


---

## Additional Notes
- Ensure MongoDB is running before starting the backend.
- Adjust CORS settings in Spring Boot if deploying on a remote server.
- Logs are available in the console for debugging purposes.

