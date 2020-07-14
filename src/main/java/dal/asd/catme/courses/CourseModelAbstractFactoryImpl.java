package dal.asd.catme.courses;

public class CourseModelAbstractFactoryImpl implements ICourseModelAbstractFactory
{
    @Override
    public ICourse makeCourse()
    {
        return new Course();
    }

    @Override
    public IEnrollment makeEnrollment()
    {
        return new Enrollment();
    }
}