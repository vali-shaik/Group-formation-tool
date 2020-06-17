package dal.asd.catme.controller;

import dal.asd.catme.beans.QuestionTitle;
import dal.asd.catme.beans.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.exception.QuestionDatabaseException;
import dal.asd.catme.service.IListQuestionsService;
import dal.asd.catme.util.CatmeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    //Creating Logger
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    @RequestMapping("")
    public ModelAndView loadPage()
    {
        User currentUser=new User();

        //Fetching current User's user name
        currentUser.setBannerId(SecurityContextHolder.getContext().getAuthentication().getName());

        ModelAndView questionsPage = new ModelAndView();

        questionsPage.setViewName(CatmeUtil.QUESTION_MANAGER_HOME);

        listQuestionsService = SystemConfig.instance().getListQuestionsService();

        try
        {
            listQuestionsService.getQuestions(currentUser.getBannerId());
            List<QuestionTitle> questionList = listQuestionsService.sortByTitle();
            questionsPage.addObject("questionTitles",questionList);

        }
        catch (QuestionDatabaseException e)
        {
            String message = e.getMessage();
            questionsPage.addObject("message",message);
        }

        return questionsPage;
    }

    @GetMapping("sort")
    public ModelAndView sortTable(@RequestParam(name = "with") String sortBy)
    {

        ModelAndView questionsPage = new ModelAndView();

        questionsPage.setViewName(CatmeUtil.QUESTION_MANAGER_HOME);


        List<QuestionTitle> questionList;
        if(sortBy.equalsIgnoreCase("Date"))
        {
            questionList = listQuestionsService.sortByDate();
        }
        else
        {
            questionList = listQuestionsService.sortByTitle();
        }
        questionsPage.addObject("questionTitles",questionList);
        questionsPage.addObject("sortBy",sortBy);

        return questionsPage;
    }
}
