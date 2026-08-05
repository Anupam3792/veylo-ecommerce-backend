package com.ecommerce.service;

import com.ecommerce.model.Otp;
import com.ecommerce.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private JavaMailSender mailSender;

    private static final int OTP_LENGTH = 6;
    private static final int EXPIRY_MINUTES = 5;

    public void generateAndSendOtp(String email) {
        String code = generateCode();

        Otp otp = new Otp();
        otp.setEmail(email);
        otp.setCode(code);
        otp.setExpiresAt(LocalDateTime.now().plusMinutes(EXPIRY_MINUTES));
        otp.setVerified(false);
        otpRepository.save(otp);

        sendEmail(email, code);
    }

    public boolean verifyOtp(String email, String code) {
        Otp otp = otpRepository.findTopByEmailOrderByIdDesc(email)
                .orElseThrow(() -> new RuntimeException("No OTP found for this email. Please request one first."));

        if (otp.isVerified()) {
            throw new RuntimeException("This OTP has already been used.");
        }
        if (LocalDateTime.now().isAfter(otp.getExpiresAt())) {
            throw new RuntimeException("OTP has expired. Please request a new one.");
        }
        if (!otp.getCode().equals(code)) {
            throw new RuntimeException("Incorrect OTP. Please try again.");
        }

        otp.setVerified(true);
        otpRepository.save(otp);
        return true;
    }

    public boolean isEmailVerified(String email) {
        return otpRepository.findTopByEmailOrderByIdDesc(email)
                .map(otp -> otp.isVerified() && LocalDateTime.now().isBefore(otp.getExpiresAt().plusMinutes(10)))
                .orElse(false);
    }

    private String generateCode() {
        SecureRandom random = new SecureRandom();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    private void sendEmail(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your Veylo verification code");
        message.setText("Your OTP is: " + code + "\n\nThis code expires in " + EXPIRY_MINUTES + " minutes.\n\nIf you didn't request this, you can safely ignore this email.");
        mailSender.send(message);
    }
}