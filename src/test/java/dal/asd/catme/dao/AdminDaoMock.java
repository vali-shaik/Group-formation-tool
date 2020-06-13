package dal.asd.catme.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.util.CatmeUtil;

public class AdminDaoMock implements IAdminDao{

	@Override
	public int addCourse(Course course) {
		List<Course> courses = new ArrayList<Course>();
		if(courses.add(course)) {
			return CatmeUtil.ONE;
		}
		return CatmeUtil.ZERO;
	}

	@Override
	public int deleteCourse(String courseId) {
		List<Course> courses= formCourseList();
		Course course = courses.get(CatmeUtil.ZERO);
		if(course.getCourseId().equalsIgnoreCase(courseId)){
			courses.remove(CatmeUtil.ZERO);
			return CatmeUtil.ONE;
		}
		return CatmeUtil.ZERO;
	}

	@Override
	public int addInstructorToCourse(String user, String course) {
		Map<String,String> userCourseMapping = new HashMap<String,String>();
		if(userCourseMapping.put(course, user) == null) {
			return CatmeUtil.ONE;
		}
		return CatmeUtil.ZERO;
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
