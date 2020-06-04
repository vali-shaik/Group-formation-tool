package dal.asd.catme.beans;

import java.util.List;

public class Course {
	String courseId;
	String courseName;
	List<Student> students; //null when course is initially created
	List<TInstructor> tInstructors;//null when course is initially created
	List<Instructor> instructors;//null when course is initially created

	public Course() {}
	public Course(String webId, String advancedWebServices) {
		this.courseId = webId;
		this.courseName=advancedWebServices;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public List<TInstructor> gettInstructors() {
		return tInstructors;
	}
	public void settInstructors(List<TInstructor> tInstructors) {
		this.tInstructors = tInstructors;
	}
	public List<Instructor> getInstructors() {
		return instructors;
	}
	public void setInstructors(List<Instructor> instructors) {
		this.instructors = instructors;
	}
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", students=" + students
				+ ", tInstructors=" + tInstructors + ", instructors=" + instructors + "]";
	}
	

}
