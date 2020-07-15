package dal.asd.catme.surveytest;
import java.util.ArrayList;
import java.util.List;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.questionmanager.Question;
import dal.asd.catme.survey.ISurveyModelAbstractFactory;
import dal.asd.catme.survey.Rule;
import dal.asd.catme.survey.Survey;
import dal.asd.catme.survey.SurveyQuestion;
public class FormSurveyMock
{
	static ISurveyModelAbstractFactory surveyModelAbstractFactory=BaseAbstractFactoryImpl.instance().makeSurveyModelAbstractFactory();
	public static Survey formSurvey()
    {
       Survey survey=surveyModelAbstractFactory.makeSurvey();
       survey.setSurveyId(7);
       survey.setGroupSize(5);
       survey.setIsPublished(false);
       survey.setSurveyName("CS survey");
       List<SurveyQuestion> surveyQuesitonsList=new ArrayList<>();
       SurveyQuestion surveyQuestion=surveyModelAbstractFactory.makeSurveyQuestion();
       surveyQuestion.setSurveyQuestionId(19);
       surveyQuestion.setPriority(9);
       Question question=formQuestionsList().get(0);
       Rule rule=formRule();
       surveyQuestion.setQuestion(question);
       surveyQuestion.setRule(rule);
       surveyQuesitonsList.add(surveyQuestion);
       survey.setSurveyQuestions(surveyQuesitonsList);
       return survey;
    }
    
    public static List<SurveyQuestion> formSurveyQuestion()
    {
    	List<SurveyQuestion> surveyQuestionList=new ArrayList<SurveyQuestion>();
    	SurveyQuestion surveyQuestion=surveyModelAbstractFactory.makeSurveyQuestion();
    	surveyQuestion.setQuestion(formQuestion());
    	surveyQuestion.setRule(formRule());
    	surveyQuestion.setPriority(9);
    	surveyQuestionList.add(surveyQuestion);
		return surveyQuestionList;
    }
    
    public static SurveyQuestion formSurveyQuestionPojo()
    {
    	SurveyQuestion surveyQuestion=surveyModelAbstractFactory.makeSurveyQuestion();
    	surveyQuestion.setSurveyQuestionId(85);
    	surveyQuestion.setQuestion(formQuestion());
    	surveyQuestion.setRule(formRule());
    	surveyQuestion.setPriority(9);
    	return surveyQuestion;
    }
    
    public static List<Question> formQuestionsList()
    {
    	List<Question> questionsList=new ArrayList<>();
    	Question question=new Question();
        question.setQuestionId(22);
        question.setQuestionText("Rate youself in Java");
        question.setQuestionType("Numeric");
        questionsList.add(question);
        Question question2=new Question();
        question2.setQuestionId(23);
        question2.setQuestionText("Do you know Heroku??");
        question2.setQuestionType("FreeText");
        questionsList.add(question2);
		return questionsList;
    	
    }
    
    public static Question formQuestion()
    {
    	Question question=new Question();
        question.setQuestionId(22);
        question.setQuestionText("Rate youself in Python");
        question.setQuestionType("Multiple choice");
        return question;
    }
    
    public static Rule formRule()
    {
    	Rule rule=surveyModelAbstractFactory.makeRule();
    	rule.setRuleId(78);
    	rule.setRuleType("Group Similar");
    	rule.setRuleValue("9 rating");
    	return rule;
    }


}
