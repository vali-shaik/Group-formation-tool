package dal.asd.catme.accesscontrol;

import java.util.List;
import java.util.Map;

import dal.asd.catme.courses.Course;

public class Admin extends User
{
    List<Course> overallCourses;	

    @Override
	public String getBannerId() {
		return super.getBannerId();
	}

	@Override
	public void setBannerId(String bannerId) {
		super.setBannerId(bannerId);
	}

	@Override
	public String getLastName() {
		return super.getLastName();
	}

	@Override
	public void setLastName(String lastName) {
		super.setLastName(lastName);
	}

	@Override
	public String getFirstName() {
		return super.getFirstName();
	}

	@Override
	public void setFirstName(String firstName) {
		super.setFirstName(firstName);
	}

	@Override
	public String getEmail() {
		return super.getEmail();
	}

	@Override
	public void setEmail(String email) {
		super.setEmail(email);
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public void setPassword(String password) {
		super.setPassword(password);
	}

	@Override
	public List<Role> getRole() {
		return super.getRole();
	}

	@Override
	public void setRole(List<Role> role) {
		super.setRole(role);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

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
