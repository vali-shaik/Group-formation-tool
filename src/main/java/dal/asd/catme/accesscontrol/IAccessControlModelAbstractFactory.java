package dal.asd.catme.accesscontrol;

public interface IAccessControlModelAbstractFactory
{
    IRole makeRole();
    IUser makeUser();
}
