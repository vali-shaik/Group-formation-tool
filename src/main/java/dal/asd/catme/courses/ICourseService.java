package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.CatmeException;

import java.util.List;

public interface ICourseService
{
    public List<ICourse> getCourses(String role) throws CatmeException;

    public List<ICourse> getAllCourses();

    public ICourse displayCourseById(String courseId) throws CatmeException;

    public String findRoleByCourse(IUser user, String courseId) throws CatmeException;

    public List<IUser> getEnrolledStudents(String courseId);
}
