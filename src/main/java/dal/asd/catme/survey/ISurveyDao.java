package dal.asd.catme.survey;

import java.util.List;

import dal.asd.catme.courses.Course;
import dal.asd.catme.exception.QuestionDatabaseException;
import dal.asd.catme.questionmanager.Question;

public interface ISurveyDao {
	public Survey getSurvey(String courseId) throws QuestionDatabaseException;
	public List<Question> fetchCourseSurveyQuestions(String courseId) throws QuestionDatabaseException;
    public Rule getSurveyQuestionRule(int questionId) throws QuestionDatabaseException;
    public int addToSurvey(Survey survey,Question question) throws QuestionDatabaseException;
    public int saveSurvey(Survey survey) throws QuestionDatabaseException;
    public int deleteSurveyQuestion(Survey survey,Question question) throws QuestionDatabaseException;
    public boolean isSurveyPublished(Course course) throws QuestionDatabaseException;
    public int publishSurvey(Survey survey) throws QuestionDatabaseException;
    public int getSurveyQuestionPriority(Survey survey,Question question) throws QuestionDatabaseException;
}

