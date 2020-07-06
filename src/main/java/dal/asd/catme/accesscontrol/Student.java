package dal.asd.catme.accesscontrol;

import java.util.List;

import dal.asd.catme.courses.Course;

public class Student extends User
{
    public List<Course> enrolledCourses;
    
	public Student()
    {
    }

    public Student(String bannerId, String lastName, String firstName, String email, String password, List<Role> role, List<Course> enrolledCourses)
    {
        super(bannerId, lastName, firstName, email, password, role);
        this.enrolledCourses = enrolledCourses;
    }

    public Student(String bannerId, String lastName, String firstName, String email, String password, List<Role> role)
    {
        super(bannerId, lastName, firstName, email, password, role);
    }

    public Student(String bannerId, String lastName, String firstName, String email, String password)
    {
        super(bannerId, lastName, firstName, email, password);
    }

    public Student(String bannerId, String lastName, String firstName, String email)
    {
        super(bannerId, lastName, firstName, email);
    }

	public List<Course> getEnrolledCourses() {
		return enrolledCourses;
	}

	public void setEnrolledCourses(List<Course> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}

}
