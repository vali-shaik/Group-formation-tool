package dal.asd.catme.service;

import dal.asd.catme.util.CatmeUtil;

public class QuestionManagerServiceImpl implements IQuestionManagerService{
		public String findQuestionType(String questionType)
		{
			if(questionType.equalsIgnoreCase(CatmeUtil.FREE_TEXT)||questionType.equalsIgnoreCase(CatmeUtil.NUMERIC))
			{
				createQuestion();
				return CatmeUtil.QUESTION_CREATION_SUCCESS;
			}else {
				return CatmeUtil.OPTION_EDITOR;
			}
		}
		
		public String createQuestion()
		{
			return null;
		}
	}
