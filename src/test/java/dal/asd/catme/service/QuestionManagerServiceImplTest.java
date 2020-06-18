package dal.asd.catme.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dal.asd.catme.dao.IQuestionDao;
import dal.asd.catme.dao.QuestionDaoMock;
import org.junit.jupiter.api.Test;

import dal.asd.catme.beans.Option;
import dal.asd.catme.beans.Question;
import dal.asd.catme.util.CatmeUtil;

public class QuestionManagerServiceImplTest {
	
	IQuestionDao questionDaoMock = new QuestionDaoMock();
	Option option;
	
	@Test
	public void createQuestionTest() {
		List<Option> options = formQuestionOptions();
		Question question = new Question(CatmeUtil.ONE,"Which Framework?","Free Text","UI",options,new Date());
		assertEquals(CatmeUtil.ONE, questionDaoMock.createQuestion(question, CatmeUtil.USER_ROLE_ID));
	
	}
	
	@Test
	public void createQuestionTitle() {
		assertEquals(CatmeUtil.ONE, questionDaoMock.createQuestionTitle(CatmeUtil.QUESTION_TITLE, CatmeUtil.USER_ROLE_ID));
	}

	@Test
	public void createOptions() {
		List<Option> options = formQuestionOptions();
		assertEquals(CatmeUtil.ONE, questionDaoMock.createOptions(CatmeUtil.QUESTION_ID, options));
	}
	
	private List<Option> formQuestionOptions(){
		List<Option> options = new ArrayList<Option>();
		option = new Option("Angular",1);
		option = new Option("React",2);
		options.add(option);
		return options;
		
	}

}
