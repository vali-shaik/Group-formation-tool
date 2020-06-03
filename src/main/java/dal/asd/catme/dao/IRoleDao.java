package dal.asd.catme.dao;


import dal.asd.catme.beans.Enrollment;

public interface IRoleDao {
	public int assignRole(String bannerId, int roleId);
	public int addInstructor(int courseId, int userRoleId);
	public int assignTa(Enrollment user);
	public int checkCourseInstructor(String bannerId, int courseId);
	public int checkUserRole(String bannerId, int roleId);
	public int getUserRoleId(String bannerId, int roleId);

}
