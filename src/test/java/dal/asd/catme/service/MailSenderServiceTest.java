package dal.asd.catme.service;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;

import static  org.junit.jupiter.api.Assertions.*;

import dal.asd.catme.mock.JavaMailSenderMock;
import org.junit.jupiter.api.Test;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class MailSenderServiceTest
{


    @Test
    void sendMailTest()
	{

		 Student s = new Student("B00101010","Test","User","test@mail.com"); String
		 sub = "This is subject of mail"; String body =
		 "You are registered in new course";

		 MailSenderService mailSenderService = new MailSenderService(new
		 JavaMailSenderMock(s,sub,body));

		 try { mailSenderService.sendMail(s,sub,body); }
		 catch (MessagingException e)
		 { e.printStackTrace(); }

	}
    
    @Test
    void getFormattedEmailForNewStudent()
    {

        MailSenderService mailSenderService = new MailSenderService(new JavaMailSenderImpl());

        Student s = new Student("B00851820","Prajapati","Tapan","Tapan.Prajapati@dal.ca","ABCE@1234");

        Course c = new Course();
        c.setCourseId("5308");

        try
        {
            assertNotNull(mailSenderService.getFormattedEmailForNewStudent(s,c));
        }
        catch (MailException e)
        {
            e.printStackTrace();
        }
    }


}
