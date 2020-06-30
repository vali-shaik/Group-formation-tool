package dal.asd.catme.service;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.beans.User;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;

import static dal.asd.catme.util.MailSenderUtil.*;

public class MailSenderServiceImpl implements IMailSenderService
{
    private JavaMailSenderImpl mailSender;

    public MailSenderServiceImpl(JavaMailSenderImpl mailSender)
    {
        this.mailSender = mailSender;
    }

    public MailSenderServiceImpl()
    {
        this.mailSender = new JavaMailSenderImpl();
        this.mailSender.setHost(HOST);
        this.mailSender.setPassword(PASSWORD);
        this.mailSender.setPort(PORT);
        this.mailSender.setUsername(USERNAME);

        Properties mailProperties = new Properties();

        mailProperties.put("mail.smtp.starttls.enable", STARTTLS_ENABLE);

        this.mailSender.setJavaMailProperties(mailProperties);
    }


    @Override
    public void sendMail(User user, String subject, String bodyText) throws MailException, MessagingException
    {
        //code taken from https://stackoverflow.com/questions/5289849/how-do-i-send-html-email-in-spring-mvc
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setText(bodyText, true);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);

        mailSender.send(mimeMessage);
    }

    @Override
    public void sendCredentialsToStudent(Student s, Course c) throws MessagingException
    {
        String bodyText = getFormattedEmailForNewStudent(s, c);

        if (bodyText == null)
            throw new MessagingException("Error Creating Mail Text From Template");

        String subject = NEW_STUDENT_EMAIL_SUBJECT;

        sendMail(s, subject, bodyText);
    }

    @Override
    public void sendResetLink(User u) throws MailException, MessagingException
    {
        sendMail(u, FORGOT_PASSWORD_EMAIL_SUBJECT, getFormattedEmailForForgotPassword(u));
    }

    public String getFormattedEmailForNewStudent(Student s, Course c)
    {
        try
        {
            File file = new File(PATH_TO_NEW_STUDENT_TEMPLATE);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            String str = new String(data, "UTF-8");

            str = str.replace(TEMPLATE_USERNAME, s.getFirstName());
            str = str.replace(TEMPLATE_BANNERID, s.getBannerId());
            str = str.replace(TEMPLATE_PASSWORD, s.getPassword());
            str = str.replace(TEMPLATE_COURSE, c.getCourseId());

            return str;
        } catch (FileNotFoundException e)
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

    public String getFormattedEmailForForgotPassword(User u)
    {
        try
        {
            File file = new File(PATH_TO_FORGOT_PASSWORD_TEMPLATE);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            String str = new String(data, "UTF-8");

            str = str.replace(TEMPLATE_USERNAME, u.getFirstName());
            str = str.replace(TEMPLATE_RESETLINK, RESETLINK + u.getPassword());

            return str;
        } catch (FileNotFoundException e)
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
