package dal.asd.catme.dao;

import dal.asd.catme.beans.Enrollment;

public interface IRoleDao {
	public int assignRole(String bannerId, int roleId);
	public int addInstructor(String courseId, int userRoleId);
	public String assignTa(Enrollment user);
	public int checkCourseInstructor(String bannerId, String courseId);
	public int checkUserRole(String bannerId, int roleId);
	public int getUserRoleId(String bannerId, int roleId);

}
