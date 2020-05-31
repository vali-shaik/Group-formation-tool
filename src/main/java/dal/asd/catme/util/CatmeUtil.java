package dal.asd.catme.util;

//Utility class for constants and Utility methods
public class CatmeUtil {
	//html file names
	public static final String HOME_PAGE="home";
	public static final String LOGIN_PAGE="login";
	public static final String ADMIN_PAGE="admin";
	public static final String ERROR_PAGE="error";
	//Roles
	public static final String GUEST_ROLE="ROLE_GUEST";
	public static final String STUDENT_ROLE="ROLE_STUDENT";
	public static final String TA_ROLE="ROLE_TA"; 
	public static final String INSTRUCTOR_ROLE="ROLE_INSTRUCTOR";
	public static final String ADMIN_ROLE="ROLE_ADMIN";	
	//SQL queries
	public static final String SELECT_COURSE_QUERY = "Select * from Course;";
	//Course table
	public static final String COURSE_ID_FIELD="courseId";
	public static final String COURSE_NAME_FIELD="courseName";


}
