package dal.asd.catme.service;

import dal.asd.catme.beans.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService implements IMailSenderService
{
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    @Autowired
    MailSenderService(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }
    @Override
    public void sendMail(Student student) throws MailException
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(student.getEmail());
        mailMessage.setFrom(env.getProperty("spring.mail.username"));
        mailMessage.setSubject("You are registered to a new subject");
        mailMessage.setText("Your credentials to access the CATME portal are as follow");

        mailSender.send(mailMessage);
    }
}
