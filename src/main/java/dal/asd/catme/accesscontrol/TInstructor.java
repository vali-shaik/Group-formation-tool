package dal.asd.catme.accesscontrol;

import java.util.List;

import dal.asd.catme.courses.Course;

public class TInstructor extends User
{
    List<Course> coursesHandled;

	public List<Course> getCoursesHandled() {
		return coursesHandled;
	}

	public void setCoursesHandled(List<Course> coursesHandled) {
		this.coursesHandled = coursesHandled;
	}

}
