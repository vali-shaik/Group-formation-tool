package dal.asd.catme.algorithm;


public class AlgorithmAbstractFactoryImpl implements IAlgorithmAbstractFactory
{
    private static IAlgorithmAbstractFactory abstractFactory = null;
    private final IGroupFormationDao groupFormationDao;
    private IGroupFormationService groupFormationService;
    private final IListGroupsService listGroupsService;

    public AlgorithmAbstractFactoryImpl()
    {
        groupFormationDao = new GroupFormationDaoImpl();

        listGroupsService = new ListGroupsServiceImpl();
    }

    public static IAlgorithmAbstractFactory instance()
    {
        if (abstractFactory == null)
        {
            abstractFactory = new AlgorithmAbstractFactoryImpl();
        }
        return abstractFactory;
    }

    @Override
    public IGroupFormationDao makeGroupFormationDao()
    {
        return groupFormationDao;
    }

    @Override
    public IGroupFormationService makeGroupFormationService(int surveyId)
    {
        return groupFormationService = new GroupFormationServiceImpl(surveyId);
    }

    @Override
    public IListGroupsService makeListGroupService()
    {
        return listGroupsService;
    }
}
