package dal.asd.catme.accesscontrol;

import java.util.List;

public interface IUser
{

    String getBannerId();
    void setBannerId(String bannerId);
    String getLastName();
    void setLastName(String lastName);
    String getFirstName();
    void setFirstName(String firstName);
    String getEmail();
    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    List<IRole> getRole();

    void setRole(List<IRole> role);
}
