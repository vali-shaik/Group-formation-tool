package dal.asd.catme.util;

//Utility class for constants and Utility methods
public class CatmeUtil {
	public static final String HOME_PAGE="home";
	public static final String LOGIN_PAGE="login";
	public static final String ADMIN_PAGE="admin";
	public static final String COURSE_ID="courseId";
	public static final String COURSE_NAME="courseName";
	public static final String ASDC_ID="5308";
	public static final String ASDC="ASDC";
	public static final String ADMIN="adminPage";
	public static final String ADD_COURSE="addCourse";
	public static final String ERROR_PAGE="errorPage";
	public static final String DELETE_COURSE="deleteCourse";
	public static final String SELECT_COURSE = "Select * from Course;";
	public static final String DELETE_COURSE_INSTRUCTOR_QUERY = "delete from CourseInstructor where CourseId=?";
	public static final String DELETE_ENROLLMENT_QUERY = "delete from Enrollment where CourseId=?";
	public static final String DELETE_COURSE_QUERY = "delete from Course where CourseId=?";
			

}
