package dal.asd.catme.studentlistimport;

import dal.asd.catme.beans.Student;
import dal.asd.catme.service.MailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

public class MailSenderServiceTest
{

    @Autowired
    MailSenderService mailSenderService;

    @Test
    void sendMailTest()
    {
        Student s = new Student();
        s.setFirstName("TestFirstname");
        s.setLastName("TestLastname");
        s.setEmail("Samkit@dal.ca");

        try
        {
            mailSenderService.sendMail(s);
        }
        catch (MailException e)
        {
            e.printStackTrace();
        }
    }
}
