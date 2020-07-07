package dal.asd.catme.accesscontrol;

import java.util.List;
import java.util.Map;

import dal.asd.catme.courses.Course;

public class Admin extends User
{
    List<Course> overallCourses;
	Map<Course, List<Instructor>> courseIntrutor;

	public List<Course> getOverallCourses() {
		return overallCourses;
	}

	public void setOverallCourses(List<Course> overallCourses) {
		this.overallCourses = overallCourses;
	}

	public Map<Course, List<Instructor>> getCourseIntrutor() {
		return courseIntrutor;
	}

	public void setCourseIntrutor(Map<Course, List<Instructor>> courseIntrutor) {
		this.courseIntrutor = courseIntrutor;
	}
    
}
