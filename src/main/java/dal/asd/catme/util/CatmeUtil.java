package dal.asd.catme.util;

//Utility class for constants and Utility methods
public class CatmeUtil 
{
	//html file names
	public static final String HOME_PAGE="home";
	public static final String LOGIN_PAGE="login";
	public static final String ADMIN_PAGE="admin";
	public static final String ERROR_PAGE="error";
	public static final String COURSE_PAGE="course";
	//Landing pages
	public static final String ADMIN_HOME="admin/dashboard";
	public static final String INSTRUCTOR_HOME="profile/instructor";
	public static final String TA_HOME="profile/ta";
	public static final String STUDENT_HOME="profile/student";
	public static final String GUEST_HOME="profile/guest";
	
	//Roles
	public static final String GUEST_ROLE="ROLE_GUEST";
	public static final String STUDENT_ROLE="ROLE_STUDENT";
	public static final String TA_ROLE="ROLE_TA"; 
	public static final String INSTRUCTOR_ROLE="ROLE_INSTRUCTOR";
	public static final String ADMIN_ROLE="ROLE_ADMIN";	
	//Security Configuration
	
	public static final String SELECT_USER_SECURITY_QUERY="select BannerId,password, enabled from User where BannerId=?";
	//public static final String SELECT_USER_SECURITY_QUERY="select BannerId,password from User where BannerId=?";
	public static final String SELECT_ROLE_SECURITY_QUERY="select UR.BannerId, R.RoleName from UserRole UR,Role R where BannerId=? and UR.RoleId=R.RoleId";
	//SQL queries
	public static final String SELECT_GUEST_COURSES_QUERY = "Select * from Course";
	public static final String SELECT_TA_COURSES_QUERY = "SELECT c.CourseId,c.CourseName FROM Course c,Enrollment e where c.CourseId=e.CourseId and e.BannerId=\"B00835822\" UNION\r\n" + 
			" SELECT c.CourseId,c.CourseName FROM Course c,CourseInstructor ci,UserRole ur where c.CourseId=ci.CourseId and ci.UserRoleId=ur.UserRoleId and ur.BannerId=\"B00835822\";";
	public static final String SELECT_INSTRUTOR_COURSES_QUERY = "SELECT c.CourseId,c.CourseName FROM Course c,CourseInstructor ci,UserRole ur where c.CourseId=ci.CourseId and ci.UserRoleId=ur.UserRoleId and ur.BannerId=";
	public static final String SELECT_COURSE_ROLE_QUERY ="SELECT r.RoleName FROM Course c,CourseInstructor ci,UserRole ur,Role r where c.CourseId=ci.CourseId and ci.UserRoleId=ur.UserRoleId and r.RoleId=ur.RoleId and ur.BannerId=";
	public static final String SELECT_COURSE_QUERY = "Select * from Course where courseId=";
	public static final String SELECT_STUDENT_COURSES_QUERY = "SELECT c.CourseId,c.CourseName FROM Course c,Enrollment e where c.CourseId=e.CourseId and e.BannerId=";
	//Course table
	public static final String COURSE_ID_FIELD="courseId";
	public static final String COURSE_NAME_FIELD="courseName";
	//UserRole table fields	
	public static final String ROLE_NAME_FIELD = "RoleName";
	


}
