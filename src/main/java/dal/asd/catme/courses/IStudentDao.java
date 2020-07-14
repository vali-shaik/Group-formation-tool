package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;

import java.sql.Connection;

public interface IStudentDao
{
    boolean enroll(IUser u, ICourse c, Connection con);
}
