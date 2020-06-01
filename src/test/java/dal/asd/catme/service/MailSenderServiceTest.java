package dal.asd.catme.service;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.service.MailSenderService;
import static  org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailSenderServiceTest
{

    MailSenderService mailSenderService = new MailSenderService(new JavaMailSenderImpl());
    @Test
    void getFormattedMailTest()
    {

        Student s = new Student("B00851820","Prajapati","Tapan","Tapan.Prajapati@dal.ca","ABCE@1234");

        Course c = new Course();
        c.setCourseName("CSCI5308");

        try
        {
            assertNotNull(mailSenderService.getFormattedEmailBody(s,c));
        }
        catch (MailException e)
        {
            e.printStackTrace();
        }
    }

}
