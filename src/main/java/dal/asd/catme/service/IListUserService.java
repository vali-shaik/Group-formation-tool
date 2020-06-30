package dal.asd.catme.service;

import java.util.List;
import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;

public interface IListUserService {
	List<User> getUsers(Course course);
}
