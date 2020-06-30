package dal.asd.catme.beans;

public class Enrollment
{
    public String bannerId;
    public String courseId;

    public Enrollment()
    {
    }

    public Enrollment(String bannerId, String courseId)
    {
        super();
        this.bannerId = bannerId;
        this.courseId = courseId;
    }

    public String getBannerId()
    {
        return bannerId;
    }

    public void setBannerId(String bannerId)
    {
        this.bannerId = bannerId;
    }

    public String getCourseId()
    {
        return courseId;
    }

    public void setCourseId(String courseId)
    {
        this.courseId = courseId;
    }
}
