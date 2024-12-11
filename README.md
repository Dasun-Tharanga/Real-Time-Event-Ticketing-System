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
- **Node.js and npm**:
  - Node.js: Version 14.x or later.
  - npm: Version 6.x or later.
- **MongoDB**: Running locally on `localhost:27017`.
- **Web Browser**: Google Chrome or Firefox recommended.

---

## Setup Instructions

### Backend Setup (Spring Boot Application)
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repo-name.git
   cd CLIENT_extracted
   ```

2. **Build the Backend Project**:
   Use Maven to clean and build the project:
   ```bash
   mvn clean install
   ```

3. **Configure MongoDB Connection**:
   Update `application.properties` with your MongoDB settings (if required):
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/ticketing-system
   ```

4. **Run the Backend Server**:
   ```bash
   mvn spring-boot:run
   ```
   The backend will start on **http://localhost:8080**.

### Frontend Setup (Angular Application)
1. **Navigate to the Frontend Directory**:
   ```bash
   cd frontend
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
- **Backend**: Ensure the Spring Boot server is running on port `8080`.
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

## Directory Structure
```
CLIENT_extracted/
   ├── src/
   │   ├── main/
   │   │   ├── java/
   │   │   │   └── com/example/ticketing/
   │   │   │       ├── controllers/       # REST Controllers
   │   │   │       ├── services/          # Service Layer
   │   │   │       └── models/            # Data Models
   │   │   └── resources/
   │   │       ├── application.properties # Application Configurations
   │   │       └── static/                # Static Resources
   ├── pom.xml                            # Maven Configuration File
   ├── README.md                          # Project Documentation
   └── frontend/
       ├── package.json                   # Frontend Dependencies
       ├── angular.json                   # Angular Project Configurations
       └── src/app/                       # Angular Source Code
```

---

## Contributing
We welcome contributions! Follow these steps:
1. **Fork the Repository**.
2. **Create a Feature Branch**:
   ```bash
   git checkout -b feature/your-feature
   ```
3. **Commit Your Changes**.
4. **Push to Your Branch**:
   ```bash
   git push origin feature/your-feature
   ```
5. **Submit a Pull Request**.

---

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.

---

## Additional Notes
- Ensure MongoDB is running before starting the backend.
- Adjust CORS settings in Spring Boot if deploying on a remote server.
- Logs are available in the console for debugging purposes.

