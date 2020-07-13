package dal.asd.catme.courses;

public interface ICourseModelAbstractFactory
{
    ICourse createCourse();
    IEnrollment createEnrollment();
}
