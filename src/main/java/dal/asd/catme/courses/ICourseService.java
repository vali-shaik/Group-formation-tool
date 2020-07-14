package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.CatmeException;

import java.util.List;

public interface ICourseService
{
    List<ICourse> getCourses(String role) throws CatmeException;

    List<ICourse> getAllCourses();

    ICourse displayCourseById(String courseId) throws CatmeException;

    String findRoleByCourse(IUser user, String courseId) throws CatmeException;

    List<IUser> getEnrolledStudents(String courseId);
}
