
package dal.asd.catme.accesscontrol;


import java.util.List;

public interface IUserService
{
    public String addUser(IUser user);

    List<IUser> getUsers();

}
