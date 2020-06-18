package dal.asd.catme.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import dal.asd.catme.beans.Option;
import dal.asd.catme.beans.Question;
import dal.asd.catme.dao.IQuestionManagerDao;
import dal.asd.catme.dao.QuestionManagerDaoMock;
import dal.asd.catme.util.CatmeUtil;

public class QuestionManagerServiceImplTest {
	
	IQuestionManagerDao questionManagerDaoMock = new QuestionManagerDaoMock();
	Option option;
	
	@Test
	public void createQuestionTest() {
		List<Option> options = formQuestionOptions();
		Question question = new Question(CatmeUtil.ONE,"Which Framework?","Free Text","UI",options,new Date());
		assertEquals(CatmeUtil.ONE,questionManagerDaoMock.createQuestion(question, CatmeUtil.USER_ROLE_ID));
	
	}
	
	@Test
	public void createQuestionTitle() {
		assertEquals(CatmeUtil.ONE,questionManagerDaoMock.createQuestionTitle(CatmeUtil.QUESTION_TITLE, CatmeUtil.USER_ROLE_ID));
	}

	@Test
	public void createOptions() {
		List<Option> options = formQuestionOptions();
		assertEquals(CatmeUtil.ONE,questionManagerDaoMock.createOptions(CatmeUtil.QUESTION_ID, options));
	}
	
	private List<Option> formQuestionOptions(){
		List<Option> options = new ArrayList<Option>();
		option = new Option("Angular",1);
		option = new Option("React",2);
		options.add(option);
		return options;
		
	}

}
