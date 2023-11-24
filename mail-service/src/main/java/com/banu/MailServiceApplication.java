package com.banu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class);
    }



    //Deneme

//    private final JavaMailSender javaMailSender;
//
//    public MailServiceApplication(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void sendMail(){
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom("${java11mailusername}");
//        mailMessage.setTo("business.alperen.ikinci@gmail.com");
//        mailMessage.setSubject("SocialMediaApp Aktivasyon Kodunuz");
//        mailMessage.setText("AR!Q4");
//        javaMailSender.send(mailMessage);
//    }
}