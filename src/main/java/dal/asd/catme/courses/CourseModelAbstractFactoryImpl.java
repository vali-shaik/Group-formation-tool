package dal.asd.catme.courses;

public class CourseModelAbstractFactoryImpl implements ICourseModelAbstractFactory
{
    private static ICourseModelAbstractFactory modelAbstractFactory = null;

    public static ICourseModelAbstractFactory instance()
    {
        if(modelAbstractFactory==null)
        {
            modelAbstractFactory = new CourseModelAbstractFactoryImpl();
        }
        return modelAbstractFactory;
    }
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