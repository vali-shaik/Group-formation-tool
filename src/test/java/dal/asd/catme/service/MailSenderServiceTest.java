package dal.asd.catme.service;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.beans.User;
import dal.asd.catme.dao.JavaMailSenderMock;
import dal.asd.catme.util.RandomTokenGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;

import static dal.asd.catme.util.MailSenderUtil.TOKEN_LENGTH;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class MailSenderServiceTest
{


    @Test
    void sendMailTest()
    {

        Student s = new Student("B00101010", "Test", "User", "test@mail.com");
        String
                sub = "This is subject of mail";
        String body =
                "You are registered in new course";

        MailSenderService mailSenderService = new MailSenderService(new
                JavaMailSenderMock(s, sub, body));

        try
        {
            mailSenderService.sendMail(s, sub, body);
        } catch (MessagingException e)
        {
            e.printStackTrace();
        }

    }

    @Test
    void getFormattedEmailForNewStudentTest()
    {

        MailSenderService mailSenderService = new MailSenderService(new JavaMailSenderImpl());

        Student s = new Student("B00851820", "Prajapati", "Tapan", "Tapan.Prajapati@dal.ca", "ABCE@1234");

        Course c = new Course();
        c.setCourseId("5308");

        try
        {
            assertNotNull(mailSenderService.getFormattedEmailForNewStudent(s, c));
        } catch (MailException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void getFormattedEmailForForgotPasswordTest()
    {
        MailSenderService mailSenderService = new MailSenderService(new JavaMailSenderImpl());

        User u = new User();
        u.setBannerId("B00000000");
        u.setPassword(RandomTokenGenerator.generateRandomPassword(TOKEN_LENGTH));
        u.setFirstName("Test");

        assertNotNull(mailSenderService.getFormattedEmailForForgotPassword(u));

        User u1 = new User();
        u1.setBannerId("B00000000");
        u1.setPassword(RandomTokenGenerator.generateRandomPassword(TOKEN_LENGTH));

        try
        {

            assertNotNull(mailSenderService.getFormattedEmailForForgotPassword(u1));
            fail();
        } catch (NullPointerException n)
        {

        }
    }


}
