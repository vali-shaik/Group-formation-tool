package dal.asd.catme.survey;

public interface ISurveyModelAbstractFactory 
{
	public Survey makeSurvey();
	public Rule makeRule();
	public SurveyQuestion makeSurveyQuestion();
}
