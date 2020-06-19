package dal.asd.catme.service;

import java.util.ArrayList;
import java.util.List;

import dal.asd.catme.beans.Option;
import dal.asd.catme.beans.Question;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.dao.IQuestionDao;
import dal.asd.catme.util.CatmeUtil;

public class QuestionManagerServiceImpl implements IQuestionManagerService
{

	IQuestionDao questionDao;

	@Override
	public String findQuestionType(Question question,String user)
	{
		if(question.getQuestionType().equalsIgnoreCase(CatmeUtil.FREE_TEXT)||question.getQuestionType().equalsIgnoreCase(CatmeUtil.NUMERIC))
		{
			int result = createQuestion(question,user);
			if(result>0) {
				return CatmeUtil.QUESTION_CREATION_SUCCESS;
			}
			else{
				return CatmeUtil.QUESTION_FAILURE_PAGE;
			}
		}else {
			List<Option> options = new ArrayList<Option>();
			for(int i=1;i<=5;i++) {
				Option option= new Option(i);
				options.add(option);
			}
			question.setOptionWithOrder(options);
			return CatmeUtil.OPTION_EDITOR;
		}
	}

	@Override
	public int createQuestion(Question question,String user)
	{
		questionDao=SystemConfig.instance().getQuestionDao();
		return questionDao.createQuestion(question, user);
	}

	@Override
	public int createOptions(int questionId, List<Option> options) {
		questionDao=SystemConfig.instance().getQuestionDao();
		return questionDao.createOptions(questionId, options);
	}

	@Override
	public int deleteQuestion(int questionId) {
		questionDao=SystemConfig.instance().getQuestionDao();
		
		return questionDao.deleteQuestion(questionId);
	}
}
