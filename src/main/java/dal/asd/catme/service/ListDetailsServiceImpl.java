package dal.asd.catme.service;

import java.util.List;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.dao.IListCourseDao;
import dal.asd.catme.dao.IListUserDao;


public class ListDetailsServiceImpl implements IListCourseService,IListUserService{
	IListCourseDao listCourse;
	IListUserDao listUser;


	@Override
	public List<User> getUsers(Course course) {
		listUser=SystemConfig.instance().getListUserDao();
		return listUser.getUsers(course);

	}

	@Override
	public List<Course> getAllCourses() {
		listCourse=SystemConfig.instance().getListCourseDao();
		return listCourse.getAllCourses();
	}

}
