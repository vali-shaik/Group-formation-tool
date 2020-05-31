package dal.asd.catme.util;

//Utility class for constants and Utility methods
public class CatmeUtil {
	public static final String HOME_PAGE="home";
	public static final String LOGIN_PAGE="login";
	public static final String ADMIN_PAGE="admin";
	public static final String COURSE_ID="courseId";
	public static final String COURSE_NAME="courseName";
	public static final String BANNER_ID="bannerId";
	public static final String FIRST_NAME="firstName";
	public static final String LAST_NAME="lastName";
	public static final String ASDC_ID="5308";
	public static final String ROLE_ID="roleId";
	public static final String TA="TA";
	public static final String ASDC="ASDC";
	public static final String ADMIN="adminPage";
	public static final String USER_ROLE_ID="UserRoleId";
	public static final String ADD_COURSE="addCourse";
	public static final String STUDENT="Student";
	public static final String ERROR_PAGE="errorPage";
	public static final String SUCCESS_PAGE="successPage";
	public static final String SELECT_COURSE = "Select * from Course;";
	public static final String SELECT_COURSE_INSTRUCTOR_BY_USER_ROLE_COURSEID="select * from CourseInstructor where userRoleId=? and courseId=?";
	public static final String DELETE_COURSE_INSTRUCTOR_QUERY = "delete from CourseInstructor where CourseId=?";
	public static final String DELETE_ENROLLMENT_QUERY = "delete from Enrollment where CourseId=?";
	public static final String DELETE_COURSE_QUERY = "delete from Course where CourseId=?";
	public static final String ADD_COURSE_QUERY="insert into Course values(?,?)";
	public static final String SELECT_TA_ROLE="select * from Role where roleName=?";
	public static final String SELECT_USER_ROLE_BY_BANNERID="select * from UserRole where bannerId=?";
	public static final String INSERT_INTO_USER_ROLE="insert into UserRole(RoleId,BannerId) values(?,?)";
	public static final String INSERT_INTO_COURSE_INSTRUCTOR="insert into CourseInstructor(CourseId,UserRoleId) values(?,?)";
	public static final String LIST_USER_QUERY="select * from User where BannerId not in (select BannerId from UserRole where RoleId in (select RoleId from Role where RoleName=?))";
			

}
