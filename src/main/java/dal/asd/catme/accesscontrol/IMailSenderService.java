
package dal.asd.catme.accesscontrol;

import dal.asd.catme.courses.ICourse;
import org.springframework.mail.MailException;

import javax.mail.MessagingException;

public interface IMailSenderService
{
    void sendMail(IUser user, String subject, String messageText) throws MailException, MessagingException;

    void sendCredentialsToStudent(IUser u, ICourse c) throws MessagingException;

    void sendResetLink(IUser u) throws MessagingException;

}
