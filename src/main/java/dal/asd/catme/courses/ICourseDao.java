package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.CatmeException;

import java.sql.Connection;
import java.util.List;

public interface ICourseDao
{

    List<ICourse> getCourses(String role) throws CatmeException;

    List<ICourse> getAllCourses();

    ICourse displayCourseById(String courseId) throws CatmeException;

    String findRoleByCourse(IUser user, String courseId) throws CatmeException;

    List<IUser> getRegisteredStudents(String courseId);

    int checkCourseRegistration(String bannerId, String courseId, Connection con);

    int checkCourseExists(String courseId, Connection con);

}
