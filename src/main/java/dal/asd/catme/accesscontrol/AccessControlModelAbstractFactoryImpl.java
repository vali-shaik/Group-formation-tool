package dal.asd.catme.accesscontrol;

public class AccessControlModelAbstractFactoryImpl implements IAccessControlModelAbstractFactory
{
    private static IAccessControlModelAbstractFactory modelAbstractFactory = null;

    public static IAccessControlModelAbstractFactory instance()
    {
        if(modelAbstractFactory==null)
        {
            modelAbstractFactory = new AccessControlModelAbstractFactoryImpl();
        }
        return modelAbstractFactory;
    }
    @Override
    public IRole createRole()
    {
        return new Role();
    }

    @Override
    public IUser createUser()
    {
        return new User();
    }
}
