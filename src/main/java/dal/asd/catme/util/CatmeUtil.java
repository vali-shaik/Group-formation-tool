package dal.asd.catme.util;

//Utility class for constants and Utility methods
public class CatmeUtil {
	//html file names
	public static final String HOME_PAGE="home";
	public static final String LOGIN_PAGE="login";
	public static final String ADMIN_PAGE="admin";
	public static final String ERROR_PAGE="error";
	public static final String FORGOT_PASSWORD_PAGE="forgot-password";
	public static final String UPLOAD_CSV_PAGE="manageCourse";
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


	public static final String TEMPLATE_USERNAME = "{username}";
	public static final String TEMPLATE_BANNERID = "{bannerid}";
	public static final String TEMPLATE_PASSWORD = "{password}";
	public static final String TEMPLATE_COURSE = "{course}";

	public static final String PATH_TO_NEW_STUDENT_TEMPLATE = "src/main/java/dal/asd/catme/util/new_student_template.html";
	public static final String NEW_STUDENT_EMAIL_SUBJECT = "Registration of new course in CATME";

	public static final String PATH_TO_FORGOT_PASSWORD_TEMPLATE = "src/main/java/dal/asd/catme/util/forgot_password_template.html";
	public static final String FORGOT_PASSWORD_EMAIL_SUBJECT = "Your Password Has Been Reset Successfully";

	public static final int RANDOM_PASSWORD_LENGTH = 8;

	public static final int GUEST_ROLE_ID = 1;
	public static final int STUDENT_ROLE_ID = 2;
	public static final int TA_ROLE_ID = 3;
}
