package dal.asd.catme.surveytest;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.survey.ISurveyAbstractFactory;
import dal.asd.catme.survey.ISurveyGroupService;

public class SurveyGroupServiceImplTest 
{

	IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ISurveyAbstractFactory surveyAbstractFactory = baseAbstractFactory.makeSurveyAbstractFactory();
    
    @Test
    public void getSurveyParticipantsTest()
    {
    	 ISurveyGroupService surveyService = surveyAbstractFactory.makeSurveyGroupService();
    	 assertNotNull(surveyService.displaySurveyGroups(7));
    }
	
}
