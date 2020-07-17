package dal.asd.catme.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ListGroupsServiceImpl implements IListGroupsService
{

    private static final Logger logger = LoggerFactory.getLogger(ListGroupsServiceImpl.class);

    IListGroupsDao listGroupsDao = new ListGroupsDaoImpl();

    @Override
    public List<SurveyGroups> listGroups(int surveyId)
    {
        logger.info("***ListGroupsSeviceImpl - listGroups invoked***");
        return listGroupsDao.listGroups(surveyId);
    }

}
