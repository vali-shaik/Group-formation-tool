package dal.asd.catme.coursestest;

import dal.asd.catme.accesscontrol.Instructor;
import dal.asd.catme.accesscontrol.Student;
import dal.asd.catme.accesscontrol.TInstructor;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.courses.Course;

import java.util.ArrayList;
import java.util.List;

public class POJOMock
{
    private static List<Course> courses = new ArrayList<>();

    public static ArrayList<User> getUsers()
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

    public static ArrayList<Student> getStudents()
    {
        ArrayList<Student> listOfUsers=new ArrayList<Student>();
        Student user=new Student();
        user.setBannerId("B00835717");
        listOfUsers.add(user);
        user.setBannerId("B00835718");
        listOfUsers.add(user);

        return listOfUsers;

    }

    public static ArrayList<Instructor> getInstructors()
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

    public static ArrayList<Course> getCourses()
    {
        List<Course> listOfCourses=new ArrayList<Course>();
        Course course=new Course();
        course.setCourseId("5409");
        course.setCourseName("Adv Web Development");
        course.setStudents(getStudents());
        course.setInstructors(getInstructors());
        listOfCourses.add(course);

        Course course1=new Course();
        course1.setCourseId("5308");
        course1.setCourseName("DWDM");
        course1.setStudents(getStudents());
        course1.setInstructors(getInstructors());

        listOfCourses.add(course1);

        Course course2=new Course();
        course2.setCourseId("5306");
        course2.setCourseName("Sofware Comprehension");
        course2.setStudents(getStudents());
        course2.setInstructors(getInstructors());

        listOfCourses.add(course2);

        return (ArrayList<Course>) listOfCourses;

    }

    public static List<TInstructor> gettInstructors()
    {
        List<TInstructor> list = new ArrayList<>();
        TInstructor tInstructor = new TInstructor();
        tInstructor.setBannerId("B00121212");

        list.add(tInstructor);
        return list;
    }


}
