package dal.asd.catme.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.dao.CourseDaoMock;
import dal.asd.catme.dao.ICourseDao;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.CatmeUtil;


public class CourseServiceImplTest {
	
	
	ICourseDao courseDaoMock=new CourseDaoMock(formCourses());
	
	@Test
	public void getCoursesTest() throws CatmeException
	{
		List<Course> listOfCourses=formCourses();
		CourseServiceImpl courseServiceImpl=new CourseServiceImpl(courseDaoMock);
		courseServiceImpl.getCourses(CatmeUtil.GUEST_ROLE);
		assertEquals(listOfCourses.get(0).getCourseId(),courseServiceImpl.getCourses(CatmeUtil.GUEST_ROLE).get(0).getCourseId());
			
	}
	@Test
	public void getCoursesSizeTest() throws CatmeException
	{
		List<Course> listOfCourses=formCourses();
		CourseServiceImpl courseServiceImpl=new CourseServiceImpl(courseDaoMock);
		courseServiceImpl.getCourses(CatmeUtil.GUEST_ROLE);
		assertEquals(listOfCourses.size(),courseServiceImpl.getCourses(CatmeUtil.GUEST_ROLE).size());
			
	}
	
	@Test
	public void getCoursesNullCheckTest() throws CatmeException
	{
		CourseServiceImpl courseServiceImpl=new CourseServiceImpl(courseDaoMock);
		assertEquals(null,courseServiceImpl.getCourses(CatmeUtil.ADMIN_ROLE));
			
	}
	
	@Test
	public void displayCourseByIdTest() throws CatmeException
	{
		CourseServiceImpl courseServiceImpl=new CourseServiceImpl(courseDaoMock);
		assertEquals("DWDM",courseServiceImpl.displayCourseById("5308").getCourseName());
		assertNotEquals("Adv Cloud",courseServiceImpl.displayCourseById("5308").getCourseName());
	}
	
	@Test
	public void displayCourseByIdNotFoundCheckTest() throws CatmeException
	{
		CourseServiceImpl courseServiceImpl=new CourseServiceImpl(courseDaoMock);
		assertEquals(null,courseServiceImpl.displayCourseById("8988").getCourseName());
	}
	
	
	
	@Test
	public void findRoleByCourseTest() throws CatmeException
	{
		User user=new User();
		user.setBannerId("B00835822");
		CourseServiceImpl courseServiceImpl=new CourseServiceImpl(courseDaoMock);
		assertEquals(CatmeUtil.STUDENT_ROLE,courseServiceImpl.findRoleByCourse(user, "5409"));
		assertEquals(CatmeUtil.TA_ROLE,courseServiceImpl.findRoleByCourse(user, "5308"));
		assertEquals(CatmeUtil.GUEST_ROLE,courseServiceImpl.findRoleByCourse(user, "5306"));
		assertNotEquals(CatmeUtil.ADMIN_ROLE,courseServiceImpl.findRoleByCourse(user, "5306"));
		
	}
	
	public List<Course> formCourses()
	{
		List<Course> listOfCourses=new ArrayList<Course>();
		Course course=new Course();
		course.setCourseId("5409");
		course.setCourseName("Adv Web Development");
		listOfCourses.add(course);
		
		Course course1=new Course();
		course1.setCourseId("5308");
		course1.setCourseName("DWDM");
		listOfCourses.add(course1);
		
		Course course2=new Course();
		course2.setCourseId("5306");
		course2.setCourseName("Sofware Comprehension");
		listOfCourses.add(course2);
		
		return listOfCourses;
		
	}


}
