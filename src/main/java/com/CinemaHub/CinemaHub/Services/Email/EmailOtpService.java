package com.CinemaHub.CinemaHub.Services.Email;

import com.CinemaHub.CinemaHub.Services.OtpStorageService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class EmailOtpService {

//    @Autowired
//    private org.springframework.mail.javamail.JavaMailSender mailSender;
//
//    @Autowired
//    private OtpStorageService otpStorageService;

    public EmailOtpService(JavaMailSender mailSender, OtpStorageService otpStorageService) {
        this.mailSender = mailSender;
        this.otpStorageService = otpStorageService;
    }

    private final org.springframework.mail.javamail.JavaMailSender mailSender; // Declare as final
    private final OtpStorageService otpStorageService;



    public void sendOtp(String email) {
        String otp = generateOtp();
        otpStorageService.storeOtp(email, otp);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp + "\nIt is valid for 5 minutes.");
        mailSender.send(message);
    }

    private String generateOtp() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000)); // 6-digit OTP
    }
}