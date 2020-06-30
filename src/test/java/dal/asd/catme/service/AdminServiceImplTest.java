package dal.asd.catme.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.dao.AdminDaoMock;
import dal.asd.catme.dao.CourseDaoMock;
import dal.asd.catme.dao.IAdminDao;
import dal.asd.catme.dao.ICourseDao;
import dal.asd.catme.dao.IListCourseDao;
import dal.asd.catme.dao.IListUserDao;
import dal.asd.catme.dao.ListDetailsDaoMock;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.CatmeUtil;

public class AdminServiceImplTest {

	IAdminDao adminDaoMock=new AdminDaoMock();
	IListCourseDao listCourseDaoMock = new ListDetailsDaoMock();
	IListUserDao listUserDaoMock=new ListDetailsDaoMock();
	
	@Test
	public void getCoursesTest() throws CatmeException
	{
		List<Course> listOfCourses=formCourseList();
		assertEquals(listOfCourses.get(0).getCourseId(),listCourseDaoMock.getAllCourses().get(0).getCourseId());
			
	}
	
	 @Test
	 public void getUsersTest() { 
		 List<User> users = formUserList(); 
		 Course course = new Course(CatmeUtil.WEB_ID, CatmeUtil.ADVANCED_WEB_SERVICES);
	  assertEquals(users.get(0).getBannerId(),listUserDaoMock.getUsers(course).get(CatmeUtil.ZERO).getBannerId());
	  }
	
	 
	 
	 
	 
	 @Test public void deleteCourseTest(){
	  assertEquals(CatmeUtil.ONE,adminDaoMock.deleteCourse(CatmeUtil.WEB_ID));
	  }
	
	 
	 @Test public void addCourseTest(){ 
		 Course course = new
			  Course(CatmeUtil.WEB_ID, CatmeUtil.ADVANCED_WEB_SERVICES); 
			  assertEquals(CatmeUtil.ONE,adminDaoMock.addCourse(course));

			  }
	 
	 @Test public void addInstructorToCourseTest(){
		 
	  assertEquals(CatmeUtil.ONE,adminDaoMock.addInstructorToCourse(CatmeUtil.
	  FIRST_NAME, CatmeUtil.WEB_ID));

	  }
	
	 private List<Course> formCourseList(){
		 List<Course> courseList=new
			  ArrayList<>(); courseList.add(new Course(CatmeUtil.WEB_ID,
			  CatmeUtil.ADVANCED_WEB_SERVICES)); return courseList;
			  }
			  
			  private List<User> formUserList(){ List<User> users=new ArrayList<>();
			  users.add(new
			  User(CatmeUtil.BANNER_ID,CatmeUtil.LAST_NAME,CatmeUtil.FIRST_NAME)); return
			  users; }
}
