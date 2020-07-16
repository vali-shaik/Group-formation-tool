package dal.asd.catme.algorithm;

public interface IAlgorithmAbstractFactory {
	IGroupFormationDao makeGroupFormationDao();
	IGroupFormationService makeGroupFormationService();
	IListGroupsService makeListGroupService();
}
