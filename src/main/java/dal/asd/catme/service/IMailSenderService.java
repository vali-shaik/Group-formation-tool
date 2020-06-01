package dal.asd.catme.service;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

public interface IMailSenderService
{
    void sendMail(User user, String subject, String messageText) throws MailException, MessagingException;

    void sendCredentialsToStudent(Student s, Course c) throws MessagingException;

}
