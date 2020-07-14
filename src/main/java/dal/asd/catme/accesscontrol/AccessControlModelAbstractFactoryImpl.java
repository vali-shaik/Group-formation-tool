package dal.asd.catme.accesscontrol;

public class AccessControlModelAbstractFactoryImpl implements IAccessControlModelAbstractFactory
{
    @Override
    public IRole makeRole()
    {
        return new Role();
    }

    @Override
    public IUser makeUser()
    {
        return new User();
    }
}
