package dal.asd.catme.questionmanager;

import java.util.Date;
import java.util.List;

public interface IQuestion
{

    public int getQuestionId();
    public void setQuestionId(int questionId);
    public String getQuestionText();
    public void setQuestionText(String questionText);
    public String getQuestionType();
    public void setQuestionType(String questionType);
    public String getQuestionTitle();
    public void setQuestionTitle(String questionTitle);
    public List<IOption> getOptionWithOrder();
    public void addOption(IOption option);
    public void setOptionWithOrder(List<IOption> optionWithOrder);
    public Date getCreatedDate();
    public void setCreatedDate(Date createdDate);
}
