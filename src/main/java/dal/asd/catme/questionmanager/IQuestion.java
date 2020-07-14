package dal.asd.catme.questionmanager;

import java.util.Date;
import java.util.List;

public interface IQuestion
{

    int getQuestionId();
    void setQuestionId(int questionId);
    String getQuestionText();
    void setQuestionText(String questionText);
    String getQuestionType();
    void setQuestionType(String questionType);
    String getQuestionTitle();
    void setQuestionTitle(String questionTitle);
    List<IOption> getOptionWithOrder();
    void addOption(IOption option);
    void setOptionWithOrder(List<IOption> optionWithOrder);
    Date getCreatedDate();
    void setCreatedDate(Date createdDate);
}
