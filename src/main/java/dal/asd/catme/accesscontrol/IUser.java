package dal.asd.catme.accesscontrol;

import java.util.List;

public interface IUser
{

    public String getBannerId();
    public void setBannerId(String bannerId);
    public String getLastName();
    public void setLastName(String lastName);
    public String getFirstName();
    public void setFirstName(String firstName);
    public String getEmail();
    public void setEmail(String email);

    public String getPassword();

    public void setPassword(String password);

    public List<Role> getRole();

    public void setRole(List<Role> role);
}
