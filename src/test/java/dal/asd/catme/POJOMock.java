package dal.asd.catme;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.Role;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.ICourse;
import dal.asd.catme.util.CatmeUtil;

import java.util.ArrayList;
import java.util.List;

public class POJOMock
{
    private static final List<ICourse> courses = new ArrayList<>();

    public static ArrayList<IUser> getUsers()
    {
        List<IUser> listOfUsers = new ArrayList<>();
        IUser user = new User();
        user.setBannerId("B00835717");
        user.setRole(new ArrayList<>());
        Role r = new Role();
        r.setRoleId(String.valueOf(CatmeUtil.STUDENT_ROLE_ID));
        user.getRole().add(r);
        listOfUsers.add(user);
        IUser user1 = new User();
        user1.setBannerId("B00835718");
        user1.setRole(new ArrayList<>());
        listOfUsers.add(user1);

        return (ArrayList<IUser>) listOfUsers;

    }

    public static ArrayList<ICourse> getCourses()
    {
        List<ICourse> listOfCourses = new ArrayList<>();
        ICourse course = new Course();
        course.setCourseId("5409");
        course.setCourseName("Adv Web Development");
        listOfCourses.add(course);

        ICourse course1 = new Course();
        course1.setCourseId("5308");
        course1.setCourseName("DWDM");

        listOfCourses.add(course1);

        ICourse course2 = new Course();
        course2.setCourseId("5306");
        course2.setCourseName("Sofware Comprehension");

        listOfCourses.add(course2);

        return (ArrayList<ICourse>) listOfCourses;

    }

    public static List<String> getPublishedCourses()
    {
        List<String> publishedCourses = new ArrayList<>();

        publishedCourses.add("5308");
        return publishedCourses;
    }

}
