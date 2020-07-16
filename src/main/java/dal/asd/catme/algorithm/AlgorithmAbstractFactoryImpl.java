package dal.asd.catme.algorithm;


public class AlgorithmAbstractFactoryImpl implements IAlgorithmAbstractFactory{
	private static IAlgorithmAbstractFactory abstractFactory = null;
	private IGroupFormationDao groupFormationDao;
	private IGroupFormationService groupFormationService;
	private IListGroupsService listGroupsService;
	
	public AlgorithmAbstractFactoryImpl() {
		groupFormationDao=new GroupFormationDaoImpl();
		groupFormationService=new GroupFormationServiceImpl();
		listGroupsService=new ListGroupsServiceImpl();
	}
	
	public static IAlgorithmAbstractFactory instance()
	{
        if(abstractFactory==null)
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
	public IGroupFormationService makeGroupFormationService() {
		return groupFormationService;
	}

	@Override
	public IListGroupsService makeListGroupService() {
		return listGroupsService;
	}
}
