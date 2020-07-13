package dal.asd.catme.courses;

public interface ICourseModelAbstractFactory
{
    ICourse makeCourse();
    IEnrollment makeEnrollment();
}
