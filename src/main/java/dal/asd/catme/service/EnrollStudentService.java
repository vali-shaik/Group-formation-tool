package dal.asd.catme.service;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.beans.User;
import dal.asd.catme.dao.IRoleDao;
import dal.asd.catme.dao.IStudentDao;
import dal.asd.catme.dao.IUserDao;
import dal.asd.catme.exception.EnrollmentException;
import dal.asd.catme.util.CatmeUtil;
import dal.asd.catme.util.RandomPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;

@Service
public class EnrollStudentService implements IEnrollStudentService
{
    @Autowired
    IUserDao userDao;

    @Autowired
    IRoleDao roleDao;

    @Autowired
    IStudentDao studentDao;

    @Autowired
    IMailSenderService mailSenderService;


    public EnrollStudentService(IUserDao userDao, IRoleDao roleDao, IStudentDao studentDao, IMailSenderService mailSenderService)
    {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.studentDao = studentDao;
        this.mailSenderService = mailSenderService;
    }

    @Override
    public boolean enrollStudentsIntoCourse(ArrayList<Student> students, Course c)
    {
        for(Student s: students)
        {
            if(userDao.checkExistingUser(s.getBannerId())==0)
            {
                try
                {
                    createNewStudent(s);
                    sendCredentials(s,c);
                }
                catch (EnrollmentException e)
                {
                    System.out.println(e.getMessage()+" "+s);
                    return false;
                }
                catch (MailException e)
                {
                    System.out.println(e.getMessage()+" "+s);
                }
            }

            try
            {
                assignStudentRole(s);
                enrollStudent(s,c);
            } catch (EnrollmentException e)
            {
                System.out.println(e.getMessage()+" "+s);
                return false;
            }
        }
        return true;
    }

    @Override
    public void enrollStudent(Student s, Course c) throws EnrollmentException
    {
        if(!studentDao.enroll(s,c))
            throw new EnrollmentException("Error making entry in Enrollment table");
    }

    @Override
    public void assignStudentRole(User student) throws EnrollmentException
    {
        if(!studentDao.isStudent(student))
        {
            if(roleDao.assignRole(student.getBannerId(), CatmeUtil.STUDENT_ROLE_ID)==0)
                throw new EnrollmentException("Error assigning student role");
        }
    }

    @Override
    public void createNewStudent(User student) throws EnrollmentException
    {
        student.setPassword(RandomPasswordGenerator.generateRandomPassword(CatmeUtil.RANDOM_PASSWORD_LENGTH));

        if(userDao.addUser(student)==0)
            throw new EnrollmentException("Error creating new user for student");
    }

    @Override
    public void sendCredentials(User student, Course c) throws MailException
    {
        try
        {
            mailSenderService.sendCredentialsToStudent((Student) student,c);
        } catch (MessagingException e)
        {
            throw new MailSendException("Error sending credentials to student");
        }
    }
}
