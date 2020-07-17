package dal.asd.catme.algorithm;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.util.CatmeUtil;
import dal.asd.catme.util.DBQueriesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupFormationDaoImpl implements IGroupFormationDao
{

    private static final Logger logger = LoggerFactory.getLogger(GroupFormationDaoImpl.class);
    IAlgorithmModelAbstractFactory modelAbstractFactory = BaseAbstractFactoryImpl.instance().makeAlgorithmModelAbstractFactory();
    DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();

    @Override
    public IAlgorithmParameters setAlgorithmParameter(int surveyId)
    {
        logger.info("*** GroupFormationDaoImpl - setAlgorithmParameter invoked");
        IAlgorithmParameters algorithmParameter = modelAbstractFactory.makeAlgorithmParameters();


        Connection con = null;
        try
        {
            con = db.getConnection();
            algorithmParameter.setGroupSize(getGroupSize(con, surveyId));
            List<Question> questions = getQuestionList(con, surveyId);
            algorithmParameter.setQuestions(questions);
            algorithmParameter.setNoOfStudents(getNumberOfStudents(con, surveyId, questions, algorithmParameter));


        } catch (NullPointerException e)
        {
            logger.error("Null exception occurred in ListGroups method.");

        } catch (Exception e)
        {
            logger.error("Exeption occurred in ListGroups method.");
        }
        return algorithmParameter;

    }

    public int getGroupSize(Connection con, int surveyId) throws AlgorithmException
    {
        logger.info("*** GroupFormationDaoImpl - getGroupSize invoked");
        PreparedStatement stmt;
        try
        {
            stmt = con.prepareStatement(DBQueriesUtil.GET_GROUP_SIZE);
            stmt.setInt(1, surveyId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                return Integer.parseInt(rs.getString("GroupSize"));

            }
        } catch (SQLException e)
        {

            logger.error("Exeption occurred in getGroupSize method.");
            throw new AlgorithmException("Exeption occurred in getGroupSize method.");
        }

        return 0;
    }

    public int getNumberOfStudents(Connection con, int surveyId, List<Question> questions, IAlgorithmParameters algorithmParameters) throws AlgorithmException
    {

        logger.info("*** GroupFormationDaoImpl - getNumberOfStudents invoked");
        PreparedStatement stmt;
        Answer answerList;
        int numberOfStudents = 0;
        List<Student> studentList = new ArrayList<Student>();
        List<Answer> listOfAnswers = new ArrayList<Answer>();
        try
        {
            stmt = con.prepareStatement(DBQueriesUtil.GET_STUDENTS);
            stmt.setInt(1, surveyId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                String bannerId = rs.getString(1);
                //********** need to be changed
                Student student = new Student();
                for (int i = 0; i < questions.size(); i++)
                {
                    //********** need to be changed
                    answerList = new Answer();

                    Question question = questions.get(0);
                    int questionId = question.getQuestionId();

                    PreparedStatement statement = con.prepareStatement(DBQueriesUtil.GET_STUDENTS_WITH_ANSWERS);
                    statement.setString(2, bannerId);
                    statement.setInt(1, questionId);

                    ResultSet resultSet = statement.executeQuery();
                    List<String> answers = new ArrayList<String>();
                    while (resultSet.next())
                    {
                        String answer = resultSet.getString("Answer");

                        answers.add(answer);
                    }
                    List<Integer> convertedAnswers = new ArrayList<>();
                    for (String individualAnswer : answers)
                    {
                        int asciiValue = 0;
                        for (int j = 0; j < individualAnswer.length(); j++)
                        {
                            asciiValue += individualAnswer.charAt(j);
                        }
                        convertedAnswers.add(asciiValue);
                    }
                    answerList.setAnswers(convertedAnswers);
                    answerList.setQuestionId(questionId);
                    listOfAnswers.add(answerList);
                }

                student.setAnswers(listOfAnswers);
                student.setBannerId(bannerId);

                studentList.add(student);


            }
            algorithmParameters.setStudents(studentList);

        } catch (SQLException e)
        {
            logger.error("Exeption occurred in getNumberOfStudents method.");
            throw new AlgorithmException("Exeption occurred in getNumberOfStudents method.");
        }

        return 0;
    }

    public List<Question> getQuestionList(Connection con, int surveyId) throws AlgorithmException
    {
        List<Question> questions = new ArrayList<Question>();
        PreparedStatement stmt;
        try
        {
            stmt = con.prepareStatement(DBQueriesUtil.GET_QUESTIONS_LIST);
            stmt.setInt(1, surveyId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                Question question = new Question();
                question.setQuestionId(Integer.parseInt(rs.getString("QuestionId")));
                question.setPriority(Integer.parseInt(rs.getString("Priority")));
                question.setQuestionType(rs.getString("QuestionType"));
                if (rs.getString("RuleType").equalsIgnoreCase(CatmeUtil.SIMILAR))
                {
                    question.setRule(CatmeUtil.ONE);
                } else if (rs.getString("QuestionType").equalsIgnoreCase(CatmeUtil.DISSIMILAR))
                {
                    question.setRule(CatmeUtil.TWO);
                } else if (rs.getString("QuestionType").equalsIgnoreCase(CatmeUtil.GREATER_THAN))
                {
                    question.setRule(CatmeUtil.THREE);
                } else if (rs.getString("QuestionType").equalsIgnoreCase(CatmeUtil.LESSER_THAN))
                {
                    question.setRule(CatmeUtil.FOUR);
                }


                questions.add(question);
            }

            for (Question question : questions)
            {
                if (question.getQuestionType().equalsIgnoreCase(CatmeUtil.CHECKBOX))
                {
                    PreparedStatement statement = con.prepareStatement(DBQueriesUtil.GET_TOTAL_OPTIONS);
                    statement.setInt(1, question.getQuestionId());

                    ResultSet resultSet = stmt.executeQuery();
                    if (resultSet.next())
                    {
                        question.setTotalNoOfOptions(Integer.parseInt(resultSet.getString(1)));
                    }

                }
            }

        } catch (SQLException e)
        {
            logger.error("Exeption occurred in getQuestionList method.");
            throw new AlgorithmException("Exeption occurred in getQuestionList method.");
        }

        return questions;
    }

}
