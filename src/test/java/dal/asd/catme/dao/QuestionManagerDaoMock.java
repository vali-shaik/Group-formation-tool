package dal.asd.catme.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dal.asd.catme.beans.Option;
import dal.asd.catme.beans.Question;
import dal.asd.catme.util.CatmeUtil;

public class QuestionManagerDaoMock implements IQuestionManagerDao{

	@Override
	public int createQuestion(Question question, String user) {
		List<Question> questions = new ArrayList<Question>();
		int result = createQuestionTitle(question.getQuestionTitle(), user);
		if(result>CatmeUtil.ZERO) {
			questions.add(question);
			if(questions.get(CatmeUtil.ZERO) != null)
			{
				return CatmeUtil.ONE;
			}
		}
		return CatmeUtil.ZERO;
	}

	@Override
	public int createQuestionTitle(String questionTitle, String user) {
		Map<String,String> questionTitleInsertion = new HashMap<String,String>();
		questionTitleInsertion.put(questionTitle,user);
		if(questionTitleInsertion.get(questionTitle) != null) {
			return CatmeUtil.ONE;
		}else {
			return CatmeUtil.ZERO;
		}
	}

	@Override
	public int createOptions(int questionId, List<Option> options) {
		Map<Integer,Map> optionInsertion = new HashMap<Integer, Map>();
		Map<Integer,String> optionMap = new HashMap<Integer,String>();
		for(Option option: options) {
			optionMap.put(option.getStoredAs(),option.getDisplayText());
		}
		optionInsertion.put(questionId, optionMap);
		if(optionInsertion.get(questionId) != null) {
			return CatmeUtil.ONE;
		}else
		{
			return CatmeUtil.ZERO;
		}
	}

}
