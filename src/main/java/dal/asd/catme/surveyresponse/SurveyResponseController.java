package dal.asd.catme.surveyresponse;

import dal.asd.catme.questionmanager.*;
import dal.asd.catme.util.CatmeUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SurveyResponseController
{
    ISurveyResponseService surveyService = SurveyResponseAbstractFactoryImpl.instance().getSurveyResponseService();
    ISurveyResponseModelAbstractFactory modelAbstractFactory = SurveyResponseModelAbstractFactoryImpl.instance();

    @RequestMapping("/viewSurvey")
    public String viewSurvey(@RequestParam(name = "courseId") String courseId, Model m)
    {
        String publishedSurveyId = surveyService.isSurveyPublished(courseId);
        String bannerId = SecurityContextHolder.getContext().getAuthentication().getName();

        m.addAttribute("surveyPublished",(publishedSurveyId!=null));
        m.addAttribute("courseId",courseId);

        if(publishedSurveyId==null)
        {
            return CatmeUtil.SURVEY_PAGE;
        }

        if(publishedSurveyId==null || surveyService.isSurveyAttempted(publishedSurveyId,bannerId))
        {
            m.addAttribute("attempted",true);
            return CatmeUtil.SURVEY_PAGE;
        }

        m.addAttribute("attempted",false);
        List<ISurveyResponse> surveyQuestions = surveyService.getSurveyQuestions(publishedSurveyId);

        ISurveyResponseBinder binder = modelAbstractFactory.createSurveyResponseBinder();
        binder.setQuestionList(surveyQuestions);
        binder.setSurveyId(publishedSurveyId);
        binder.setCourseId(courseId);

        m.addAttribute("response",binder);

        return CatmeUtil.SURVEY_PAGE;
    }

    @PostMapping("/saveResponse")
    public String saveResponse(@ModelAttribute SurveyResponseBinder binder)
    {
        String bannerId = SecurityContextHolder.getContext().getAuthentication().getName();
        surveyService.saveResponses(binder,bannerId);

        for(ISurveyResponse response: binder.getQuestionList())
        {
            System.out.println(response.getQuestion().getQuestionId()+": "+response.getAnswer());
        }

        return "redirect:/viewSurvey?courseId="+binder.getCourseId();
    }
}
