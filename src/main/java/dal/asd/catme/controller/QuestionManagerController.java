package dal.asd.catme.controller;

import dal.asd.catme.beans.QuestionTitle;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.exception.QuestionDatabaseException;
import dal.asd.catme.service.IListQuestionsService;
import dal.asd.catme.util.CatmeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("questions")
public class QuestionManagerController
{
    IListQuestionsService listQuestionsService;

    @RequestMapping("")
    public ModelAndView loadPage(@RequestParam(name = "instructorId") String instructorid)
    {
        ModelAndView questionsPage = new ModelAndView();

        questionsPage.setViewName(CatmeUtil.QUESTION_MANAGER_HOME);

        listQuestionsService = SystemConfig.instance().getListQuestionsService();

        try
        {
            List<QuestionTitle> questionList = listQuestionsService.getQuestions("null");
            questionsPage.addObject("questionTitles",new ArrayList<QuestionTitle>());

        }
        catch (QuestionDatabaseException e)
        {
            String message = e.getMessage();
            questionsPage.addObject("message",message);
        }

        return questionsPage;
    }
}
