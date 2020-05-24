package dal.asd.catme.beans;

import java.util.List;

public class Course {
	String courseId;
	String courseName;
	List<Student> students; //null when course is initially created
	List<TInstructor> tInstructors;//null when course is initially created
	List<Instructor> instructors;//null when course is initially created
	
}
