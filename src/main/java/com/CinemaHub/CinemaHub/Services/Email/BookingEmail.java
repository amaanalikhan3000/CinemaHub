package com.CinemaHub.CinemaHub.Services.Email;



import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingEmail {

    public BookingEmail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private final org.springframework.mail.javamail.JavaMailSender mailSender; // Declare as final

    public void sendEmail(String username,
                          String email,
                          List<Integer> seatNos,
                          String movieName,
                          String startTime,
                          String endTime,
                          Long bookingId,
                          String theaterLocation,
                          String cinemaHallName) {

/*      // find from email
         ->   [Customer Name]
        findNameByEmail or pass from method;
        Movie Name: [Movie Name]
        Show Time: [Show Time]
        seatNos
        Seats: [Seat(s) Number]
        -> Seat No
        Booking ID: [Booking ID or Reference Number]

 */

        // Generate seat numbers as a comma-separated string
        String seatNumbers = seatNos.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        // Generate the email content dynamically
        String emailContent = String.format(
                "Dear  %s,\n\n" +
                        "Thank you for choosing CinemaHub! 🎬\n\n" +
                        "We are pleased to confirm your movie ticket booking. Here are the details:\n\n" +
                        "Movie Name: %s\n" +
                        "Show Time: %s to %s\n" +
                        "Seats: %s\n" +
                        "Booking ID: %d\n\n" +
                        "Theater Location: %s\n" +
                        "Cinema Hall: %s\n\n" +
                        "Important Notes:\n" +
                        "- Please arrive at least 15 minutes before the showtime to avoid missing the start.\n" +
                        "- Keep this email handy as proof of your booking.\n" +
                        "- If you need to modify your booking or for any inquiries, feel free to contact us or visit our website.\n\n" +
                        "We hope you enjoy your movie experience with us! 🍿📽️\n\n" +
                        "Best Regards,\n" +
                        "CinemaHub Team",
                username,movieName, startTime, endTime, seatNumbers, bookingId,theaterLocation,cinemaHallName
        );

        // Create and send the email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("🎉 Booking Confirmed: Your Movie Ticket for " + movieName + " is Successfully Booked!");
        message.setText(emailContent);

        mailSender.send(message);

    }


}
