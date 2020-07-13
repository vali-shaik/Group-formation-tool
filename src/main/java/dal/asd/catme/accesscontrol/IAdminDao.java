
package dal.asd.catme.accesscontrol;

import dal.asd.catme.courses.ICourse;

public interface IAdminDao
{

    int addCourse(ICourse Course);

    int deleteCourse(String courseId);

    int addInstructorToCourse(String user, String course);

}
