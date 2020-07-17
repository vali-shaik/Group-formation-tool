package dal.asd.catme.algorithm;

public interface IQuestion
{

    int getTotalNoOfOptions();

    void setTotalNoOfOptions(int totalNumberOfAnswerOption);

    int getRule();

    void setRule(int rule);

    int getQuestionId();

    void setQuestionId(int questionId);

    String getQuestionType();

    void setQuestionType(String questionType);

    int getPriority();

    void setPriority(int priority);
}
