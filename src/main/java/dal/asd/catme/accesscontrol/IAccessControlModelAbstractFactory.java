package dal.asd.catme.accesscontrol;

public interface IAccessControlModelAbstractFactory
{
    IRole createRole();
    IUser createUser();
}
