
# Booking Confirmation Email Service 🎬📧

This **Spring Boot** project features a robust booking confirmation email service. Users receive a detailed email notification after successfully reserving seats for a movie show. The project integrates **Spring Mail** for sending emails and seamlessly ties this functionality with the booking process.

---

## Features 🚀

### 1. **User Authentication**
- Ensures the user is authenticated before making a booking.

### 2. **Seat Reservation**
- Handles the reservation process and seat allocation.

### 3. **Email Notification**
- Sends a confirmation email with the following booking details:
  - **Movie Name**
  - **Show Time (Start and End)**
  - **Seat Numbers**
  - **Booking ID**
  - **Theater Location**
  - **Cinema Hall Name**

---

## Key Dependencies 📦

Add the following dependencies in your `pom.xml`:

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Starter Mail -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>

    <!-- Spring Boot Starter Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- MySQL Connector (or other DB of your choice) -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
</dependencies>
```

---

## How It Works 🔧

### 1. **Reserve Seats Endpoint**
The `/reserve` endpoint handles the seat reservation and sends a confirmation email.

```java
@PostMapping("/reserve")
public ResponseEntity<?> reserveSeats(@RequestBody ReservationRequest request) {
    // Step 1: Authenticate the user
    // Step 2: Validate user ID and reservation details
    // Step 3: Reserve the seats
    // Step 4: Send email confirmation using BookingEmail service
}
```

### 2. **Booking Email Service**
The `BookingEmail` service formats and sends the confirmation email.

```java
@Service
public class BookingEmail {
    private final JavaMailSender mailSender;

    public BookingEmail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String username, String email, List<Integer> seatNos, String movieName, 
                          String startTime, String endTime, Long bookingId, 
                          String theaterLocation, String cinemaHallName) {
        // Step 1: Format seat numbers
        // Step 2: Create the email content dynamically
        // Step 3: Send the email using JavaMailSender
    }
}
```

---

## Example Email Content ✉️

**Subject:** 🎉 Booking Confirmed: Your Movie Ticket for [Movie Name] is Successfully Booked!

**Body:**

```plaintext
Dear [Customer Name],

Thank you for choosing CinemaHub! 🎬

We are pleased to confirm your movie ticket booking. Here are the details:

Movie Name: [Movie Name]  
Show Time: [Start Time] to [End Time]  
Seats: [Seat Numbers]  
Booking ID: [Booking ID]  

Theater Location: [Location]  
Cinema Hall: [Hall Name]  

Important Notes:
- Please arrive at least 15 minutes before the showtime to avoid missing the start.
- Keep this email handy as proof of your booking.
- If you need to modify your booking or for any inquiries, feel free to contact us or visit our website.

We hope you enjoy your movie experience with us! 🍿📽️  

Best Regards,  
CinemaHub Team
```

---

## Prerequisites 📋

### Configure Mail Properties
Add the following properties in your `application.properties`:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### Database Configuration
Add the following database properties in your `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cinemahub
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

---

## Usage Instructions ▶️

1. **Run the Spring Boot Application**  
   Use your IDE or execute `mvn spring-boot:run`.

2. **Reserve Seats**  
   Send a POST request to `/reserve` with a JSON body like:
   ```json
   {
       "userId": 1,
       "showId": 101,
       "seatNos": [12, 13, 14]
   }
   ```

3. **Check Email**  
   The user should receive a booking confirmation email at the registered email address.

---

## Additional Notes ℹ️

### Transactional Behavior
- The booking process is wrapped in a `@Transactional` method to ensure data consistency.

### Authentication
- User authentication is handled using **Spring Security**.

---


## License 📝
This project is licensed under the **MIT License**.  
Feel free to use, modify, and distribute as needed. 🎉
