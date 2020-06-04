package dal.asd.catme.dao;


import dal.asd.catme.beans.Enrollment;

import java.sql.Connection;

public interface IRoleDao {
	public int assignRole(String bannerId, int roleId, Connection con);
	public int addInstructor(int courseId, int userRoleId, Connection con);
	public int assignTa(Enrollment user, Connection con);
	public int checkCourseInstructor(String bannerId, int courseId, Connection con);
	public int checkUserRole(String bannerId, int roleId, Connection con);
	public int getUserRoleId(String bannerId, int roleId, Connection con);

}
