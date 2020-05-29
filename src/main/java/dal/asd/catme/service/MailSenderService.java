package dal.asd.catme.service;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.beans.User;
import dal.asd.catme.util.CatmeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.ArrayList;

@Service
public class MailSenderService implements IMailSenderService
{
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    @Autowired
    public MailSenderService(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    //for test case purpose
    public MailSenderService(){}

    @Override
    public void sendMail(User user, String subject, String bodyText) throws MailException, MessagingException
    {


        //code taken from https://stackoverflow.com/questions/5289849/how-do-i-send-html-email-in-spring-mvc
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setText(bodyText, true);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setFrom(env.getProperty("spring.mail.username"));

        mailSender.send(mimeMessage);
    }

    @Override
    public void sendCredentialsToStudent(Student s, Course c) throws MessagingException
    {
        String bodyText = getFormattedEmailBody(s,c);

        if(bodyText==null)
            throw new MessagingException("Error Creating Mail Text From Template");

        String subject = CatmeUtil.EMAIL_SUBJECT;

        sendMail(s,subject,bodyText);
    }

    public void sendCredentialsToStudents(ArrayList<Student> students, Course c) throws MessagingException
    {
        for(Student s : students)
        {
            sendCredentialsToStudent(s,c);
        }
    }

    public String getFormattedEmailBody(Student s, Course c)
    {
        try
        {

            //read html email template file
            File file = new File(CatmeUtil.PATH_TO_EMAIL_TEMPLATE);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            String str = new String(data, "UTF-8");

            //replace student details in the content
            str = str.replace(CatmeUtil.TEMPLATE_USERNAME,s.getFirstName());
            str = str.replace(CatmeUtil.TEMPLATE_BANNERID,s.getBannerId());
            str = str.replace(CatmeUtil.TEMPLATE_PASSWORD,s.getPassword());
            str = str.replace(CatmeUtil.TEMPLATE_COURSE,c.getCourseName());

            return str;
        }
        catch (FileNotFoundException e)
        {
            return null;
        } catch (UnsupportedEncodingException e)
        {
            return null;
        } catch (IOException e)
        {
            return null;
        }
    }
}
