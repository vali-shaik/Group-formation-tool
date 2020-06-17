package dal.asd.catme.dao;


import java.sql.Connection;
import java.util.ArrayList;

import dal.asd.catme.beans.Question;
import dal.asd.catme.beans.QuestionTitle;


public class QuestionDaoMock implements IQuestionDao{
	
	ArrayList<QuestionTitle> questions;
	
//	public QuestionDaoMock() {
//				
//	}

    public QuestionDaoMock(ArrayList<QuestionTitle> questions)
    {
        this.questions = questions;
    }

	@Override
	public int deleteQuestion(int questionId, Connection con) {
		// TODO Auto-generated method stub
		if(0 != checkExistingQuestion(questionId, con)){
			for(QuestionTitle qT: questions)
	        {				
				for(Question q:qT.getQuestions()){
					if(q.getQuestionId()==questionId)
						questions.remove(q);
		                return 1;
				}	            
	        }
		}
		
        return 0;
	}

	@Override
	public int checkExistingQuestion(int questionId, Connection con) {
		// TODO Auto-generated method stub
		for(QuestionTitle qT: questions)
        {
			for(Question q:qT.getQuestions()){
				if(q.getQuestionId()==questionId)
	                return 1;
			}
            
        }
        return 0;
	}

}
