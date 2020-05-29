package dal.asd.catme.studentlistimport;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.service.MailSenderService;
import static  org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;
import java.util.ArrayList;

public class MailSenderServiceTest
{

    @Test
    void getFormattedMailTest()
    {
        MailSenderService mailSenderService = new MailSenderService();

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

    @Test
    void sendCredentialToStudent()
    {
        MailSenderService mailSenderService = new MailSenderService(new JavaMailSenderImpl());

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
            mailSenderService.sendCredentialsToStudent(s,c);
        }
        catch (MailException | MessagingException e)
        {
            assertTrue(false);
        }
    }

    @Test
    void sendCredentialToStudents()
    {
        MailSenderService mailSenderService = new MailSenderService(new JavaMailSenderImpl());

        ArrayList<Student> list = new ArrayList<>();

        Student s1 = new Student();
        s1.setFirstName("Tapan");
        s1.setLastName("Prajapati");
        s1.setBannerId("B00851820");
        s1.setPassword("ABCD@1234");
        s1.setEmail("Tapan.Prajapati@dal.ca");
        list.add(s1);

        Student s2 = new Student();
        s2.setFirstName("Swetha");
        s2.setLastName("Subash");
        s2.setBannerId("B0011111");
        s2.setPassword("ABCD@1234");
        s2.setEmail("Swetha.Subash@dal.ca");
        list.add(s2);


        Student s3 = new Student();
        s3.setFirstName("Nishant");
        s3.setLastName("Amoli");
        s3.setBannerId("B0011111");
        s3.setPassword("ABCD@1234");
        s3.setEmail("ns573209@dal.ca");
        list.add(s3);

        Student s4 = new Student();
        s4.setFirstName("Vali");
        s4.setLastName("Shaik");
        s4.setBannerId("B0011111");
        s4.setPassword("ABCD@1234");
        s4.setEmail("vl216084@dal.ca");
        list.add(s4);

        Course c = new Course();
        c.setCourseName("CSCI5308");

        try
        {
            mailSenderService.sendCredentialsToStudents(list,c);
        }
        catch (MailException | MessagingException e)
        {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}
