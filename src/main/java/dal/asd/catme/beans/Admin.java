package dal.asd.catme.beans;

import java.util.List;
import java.util.Map;

public class Admin extends User {

	List<Course> overallCourses;// add or remove courses

	Map<Course, List<Instructor>> courseIntrutor;

	/*
	 * {Key=> Course (CourseID) Value=>{ I1,I2,I3 }}
	 */

}
