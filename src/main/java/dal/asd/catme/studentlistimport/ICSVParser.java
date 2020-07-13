package dal.asd.catme.studentlistimport;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;

import java.util.ArrayList;

public interface ICSVParser
{
    public ArrayList<IUser> getStudentsFromFile(ICSVReader reader);
    public boolean validBannerId(String bannerId);

    public boolean validNames(String firstname, String lastname);

    public boolean validEmailId(String emailId);
}
