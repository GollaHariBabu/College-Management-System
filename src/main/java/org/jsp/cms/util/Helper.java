package org.jsp.cms.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class Helper {
	@Autowired
	private JavaMailSender javaMailSender;

	public boolean sendMail(String email, int otp) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setTo(email);
			helper.setSubject("Account Created");
			helper.setFrom("haribab369@gmail.com");
			String htmlContent = "<html> <body> <p>Your Account is Created in College_Management_System</p><h1 style='margin:auto;'>"
					+ email + " and your otp is " + otp
					+ "</h1> <p> please enter your otp to verify </p> </body> </html>";
			helper.setText(htmlContent, true);
		} catch (MessagingException e) {
			System.out.println("Invalid Email Id");
			return false;
		}
		try {
			System.out.println("Mail Sending............");
			javaMailSender.send(mimeMessage);
			System.out.println("Mail Sent...............");
		} catch (MailException e) {
			System.out.println("Invalid Email Id");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int generateOTP() {
		double otp = 0;
		while (otp < 1000) {
			otp = Math.random() * 10000;
		}
		return (int) otp;
	}

}