package dal.asd.catme.service;

import java.util.ArrayList;
import java.util.List;

import dal.asd.catme.beans.Option;
import dal.asd.catme.beans.Question;
import dal.asd.catme.dao.IQuestionDao;
import dal.asd.catme.dao.QuestionDaoImpl;
import dal.asd.catme.util.CatmeUtil;

public class QuestionManagerServiceImpl implements IQuestionManagerService{
	
		
		
		IQuestionDao questionDao = new QuestionDaoImpl();
	
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
					//need to change
					return "questionCreationFailure";
				}
			}else {
				List<Option> options = new ArrayList<Option>();
				for(int i=1;i<=5;i++) {
					Option option= new Option(i);
					options.add(option);
				}
				System.out.println("##Options  : "+options.toString());
				question.setOptionWithOrder(options);
				System.out.println("##before : "+question.toString());
				return CatmeUtil.OPTION_EDITOR;
			}
		}
		
		@Override
		public int createQuestion(Question question,String user)
		{
			return questionDao.createQuestion(question, user);
		}

		@Override
		public int createOptions(int questionId, List<Option> options) {
			return questionDao.createOptions(questionId, options);
		}
	}
