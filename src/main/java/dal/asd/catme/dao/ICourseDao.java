package dal.asd.catme.dao;

public interface ICourseDao {
	public int checkCourseRegistration(String bannerId, String courseId);
	public int checkCourseExists(String courseId);
}
