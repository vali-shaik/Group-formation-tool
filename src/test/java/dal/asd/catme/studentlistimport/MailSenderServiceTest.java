package dal.asd.catme.studentlistimport;

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

        Student s = new Student();
        s.setFirstName("Tapan");
        s.setLastName("Prajapati");
        s.setBannerId("B00851820");
        s.setPassword("ABCD@1234");
        s.setEmail("Tapan.Prajapati@dal.ca");

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
