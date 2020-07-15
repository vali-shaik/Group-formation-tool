package dal.asd.catme.courses;


import java.sql.Connection;

public interface IRoleDao
{
    int assignRole(String bannerId, int roleId, Connection con);

    int addInstructor(String courseId, int userRoleId, Connection con);

    String assignTa(Enrollment user, Connection con);

    int checkCourseInstructor(String bannerId, String courseId, Connection con);

    int checkUserRole(String bannerId, int roleId, Connection con);

    int getUserRoleId(String bannerId, int roleId, Connection con);

}
