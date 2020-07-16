package dal.asd.catme.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupFormationServiceImpl implements IGroupFormationService{
	/*
	 * List<Question> questionTypeOne = new ArrayList<>(); List students = new
	 * ArrayList<String>();
	 */
	
	IGroupFormationDao groupFormationDao = new GroupFormationDaoImpl();
	

	@Override
	public HashMap formGroups(int surveyId) {
		groupFormationDao.setAlgorithmParameter(surveyId);
		return null;
	}
	
	 
}
