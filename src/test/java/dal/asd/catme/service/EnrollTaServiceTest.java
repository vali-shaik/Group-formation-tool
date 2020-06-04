package dal.asd.catme.service;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import dal.asd.catme.beans.*;
import org.junit.jupiter.api.Test;
import dal.asd.catme.dao.RoleDaoMock;

import static org.junit.jupiter.api.Assertions.*;


public class EnrollTaServiceTest {
	Connection con;
	
	
	 @Test
	 void enrollTa()
	    {
		 	RoleDaoMock roleDaoMock = new RoleDaoMock(formUsers(),formCourses());	
	        try
	        {
	            assertNotNull(roleDaoMock.assignTa(new Enrollment("B00835717","5306"), con));

	        } catch (Exception e)
	        {
	            e.printStackTrace();
	            fail();
	        }

			try
			{
				assertNull(roleDaoMock.assignTa(new Enrollment("B00835725","5306"), con));

			} catch (Exception e)
			{
				e.printStackTrace();
				fail();
			}
	    }


	
	public ArrayList<User> formUsers()
	{
		List<User> listOfUsers=new ArrayList<User>();
		User user=new User();
		user.setBannerId("B00835717");
		listOfUsers.add(user);
		User user1 = new User();
		user1.setBannerId("B00835718");
		listOfUsers.add(user1);
		
		return (ArrayList<User>) listOfUsers;
		
	}
	public ArrayList<Student> formStudents()
	{
		ArrayList<Student> listOfUsers=new ArrayList<Student>();
		Student user=new Student();
		user.setBannerId("B00835717");
		listOfUsers.add(user);
		user.setBannerId("B00835718");
		listOfUsers.add(user);

		return listOfUsers;

	}

	public ArrayList<Instructor> formInstructors()
	{
		ArrayList<Instructor> listOfUsers=new ArrayList<Instructor>();
		Instructor user=new Instructor();
		user.setBannerId("B00835719");
		listOfUsers.add(user);
		Instructor user1 = new Instructor();
		user1.setBannerId("B00835718");
		listOfUsers.add(user1);

		return listOfUsers;

	}

	public ArrayList<Course> formCourses()
	{
		List<Course> listOfCourses=new ArrayList<Course>();
		Course course=new Course();
		course.setCourseId("5409");
		course.setCourseName("Adv Web Development");
		course.setStudents(formStudents());
		course.setInstructors(formInstructors());
		listOfCourses.add(course);
		
		Course course1=new Course();
		course1.setCourseId("5308");
		course1.setCourseName("DWDM");
		course1.setStudents(formStudents());
		course1.setInstructors(formInstructors());

		listOfCourses.add(course1);
		
		Course course2=new Course();
		course2.setCourseId("5306");
		course2.setCourseName("Sofware Comprehension");
		course2.setStudents(formStudents());
		course2.setInstructors(formInstructors());

		listOfCourses.add(course2);
		
		return (ArrayList<Course>) listOfCourses;
		
	}


}
