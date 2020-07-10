package dal.asd.catme.surveyresponse;

import dal.asd.catme.questionmanager.Option;
import dal.asd.catme.questionmanager.Question;
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
        List<SurveyResponse> surveyQuestions = surveyService.getSurveyQuestions(publishedSurveyId);

        SurveyResponseBinder binder = new SurveyResponseBinder();
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

        for(SurveyResponse response: binder.getQuestionList())
        {
            System.out.println(response.getQuestion().getQuestionId()+": "+response.getAnswer());
        }

        return "redirect:/viewSurvey?courseId="+binder.getCourseId();
    }

    private List<SurveyResponse> getList()
    {
        List<SurveyResponse> list = new ArrayList<>();

        SurveyResponse s = new SurveyResponse();
        Question q = new Question();
        q.setQuestionText("Q1");
        q.setQuestionType(CatmeUtil.CHECKBOX);
        q.addOption(new Option("1",1));
        q.addOption(new Option("2",2));

        s.setQuestion(q);

        list.add(s);


        SurveyResponse s1 = new SurveyResponse();
        Question q1 = new Question();
        q1.setQuestionText("Q2");
        q1.setQuestionType(CatmeUtil.NUMERIC_DB);

        s1.setQuestion(q1);

        list.add(s1);

        return list;
    }
}
