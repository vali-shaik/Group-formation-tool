package dal.asd.catme.accesscontrol;


import java.sql.Connection;

import dal.asd.catme.courses.Enrollment;

public interface IRoleDao
{
    public int assignRole(String bannerId, int roleId, Connection con);

    public int addInstructor(String courseId, int userRoleId, Connection con);

    public String assignTa(Enrollment user, Connection con);

    public int checkCourseInstructor(String bannerId, String courseId, Connection con);

    public int checkUserRole(String bannerId, int roleId, Connection con);

    public int getUserRoleId(String bannerId, int roleId, Connection con);

}
