package dal.asd.catme.surveyresponse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SurveyResponseAbstractFactoryTest
{
    @Test
    void getSurveyResponseDaoTest()
    {
        ISurveyResponseDao surveyResponseDao = SurveyResponseAbstractFactoryImpl.instance().getSurveyResponseDao();

        assertNotNull(surveyResponseDao);
    }

    @Test
    void getSurveyResponseServiceTest()
    {
        ISurveyResponseService surveyResponseService = SurveyResponseAbstractFactoryImpl.instance().getSurveyResponseService();

        assertNotNull(surveyResponseService);
    }
}
