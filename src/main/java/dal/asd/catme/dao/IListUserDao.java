package dal.asd.catme.dao;

import java.util.List;
import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;

public interface IListUserDao {
	List<User> getUsers(Course course);
}
