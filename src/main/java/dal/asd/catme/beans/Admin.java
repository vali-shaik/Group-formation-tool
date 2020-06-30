package dal.asd.catme.beans;

import java.util.List;
import java.util.Map;

public class Admin extends User
{
    List<Course> overallCourses;

    Map<Course, List<Instructor>> courseIntrutor;
}
