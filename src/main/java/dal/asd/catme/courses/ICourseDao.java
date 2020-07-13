
package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.CatmeException;

import java.sql.Connection;
import java.util.List;

public interface ICourseDao
{

    public List<ICourse> getCourses(String role) throws CatmeException;

    public List<ICourse> getAllCourses();

    public ICourse displayCourseById(String courseId) throws CatmeException;

    public String findRoleByCourse(IUser user, String courseId) throws CatmeException;

    public List<IUser> getRegisteredStudents(String courseId);

    public int checkCourseRegistration(String bannerId, String courseId, Connection con);

    public int checkCourseExists(String courseId, Connection con);

}
