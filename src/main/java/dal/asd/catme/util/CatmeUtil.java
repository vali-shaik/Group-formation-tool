package dal.asd.catme.util;

//Utility class for constants and Utility methods
public class CatmeUtil 
{
	public static final int TWO = 2;
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final String HOME_PAGE="home";
	public static final String LOGIN_PAGE="login";
	public static final String ADMIN_PAGE="admin";
	public static final String COURSE_ID="courseId";
	public static final String COURSE_NAME="courseName";
	public static final String BANNER_ID="bannerId";
	public static final String FIRST_NAME="firstName";
	public static final String LAST_NAME="lastName";
	public static final String ROLE_ID="roleId";
	public static final String ROLE_INSTRUCTOR="ROLE_INSTRUCTOR";
	public static final String ADVANCED_WEB_SERVICES="Advanced Web Services";
	public static final String WEB_ID="CSCI5309";
	public static final String ADMIN="adminPage";
	public static final String USER_ROLE_ID="UserRoleId";
	public static final String ADD_COURSE="addCourse";
	public static final String STUDENT="Role_Student";
	public static final String ERROR="errorPage";
	public static final String SUCCESS_PAGE="successPage";
	public static final String USER_PAGE="userPage";
	public static final String ERROR_PAGE="error";

	public static final String FORGOT_PASSWORD_PAGE="forgot-password";
	public static final String MANAGE_COURSE_PAGE ="manageCourse";

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

	//Queries
	public static final String SELECT_COURSE = "Select * from Course;";
	public static final String SELECT_COURSE_INSTRUCTOR_BY_USER_ROLE_COURSEID="select * from CourseInstructor where userRoleId=? and courseId=?";
	public static final String DELETE_COURSE_INSTRUCTOR_QUERY = "delete from CourseInstructor where CourseId=?";
	public static final String DELETE_ENROLLMENT_QUERY = "delete from Enrollment where CourseId=?";
	public static final String DELETE_COURSE_QUERY = "delete from Course where CourseId=?";
	public static final String ADD_COURSE_QUERY="insert into Course values(?,?)";
	public static final String SELECT_ROLE_BY_ROLENAME="select * from Role where roleName=?";
	public static final String SELECT_USER_ROLE_BY_BANNERID="select * from UserRole where bannerId=? and RoleId=?";
	public static final String INSERT_INTO_USER_ROLE="insert into UserRole(RoleId,BannerId) values(?,?)";
	public static final String INSERT_INTO_COURSE_INSTRUCTOR="insert into CourseInstructor(CourseId,UserRoleId) values(?,?)";
	public static final String LIST_USER_QUERY="select * from User where BannerId not in (select BannerId from UserRole where RoleId in (select RoleId from Role where RoleName=?))";
	public static final String CHECK_INSTRUCTOR="select * from Course where CourseId=?";
			

	public static final String TEMPLATE_USERNAME = "{username}";
	public static final String TEMPLATE_BANNERID = "{bannerid}";
	public static final String TEMPLATE_PASSWORD = "{password}";
	public static final String TEMPLATE_COURSE = "{course}";

	public static final String PATH_TO_NEW_STUDENT_TEMPLATE = "src/main/resources/templates/new_student_template.html";
	public static final String NEW_STUDENT_EMAIL_SUBJECT = "Registration of new course in CATME";

	public static final String PATH_TO_FORGOT_PASSWORD_TEMPLATE = "src/main/resources/templates/forgot_password_template.html";
	public static final String FORGOT_PASSWORD_EMAIL_SUBJECT = "Your Password Has Been Reset Successfully";

	public static final int RANDOM_PASSWORD_LENGTH = 8;

	public static final int GUEST_ROLE_ID = 1;
	public static final int STUDENT_ROLE_ID = 2;
	public static final int TA_ROLE_ID = 3;
}
