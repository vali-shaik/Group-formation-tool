package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.User;

import java.sql.Connection;

public interface IStudentDao
{
    boolean enroll(User u, Course c, Connection con);
}
