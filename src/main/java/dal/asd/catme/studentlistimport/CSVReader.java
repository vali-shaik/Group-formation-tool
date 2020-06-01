package dal.asd.catme.studentlistimport;

import dal.asd.catme.beans.Student;
import dal.asd.catme.exception.InvalidFileFormatException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CSVReader
{
    static int BANNERID = 0;
    static int LASTNAME = 1;
    static int FIRSTNAME = 2;
    static int EMAILID = 3;

    static int BANNERID_LENGTH = 9;
    static int CSV_COLUMNS = 4;

    public List<Student> readFile(String fileName) throws InvalidFileFormatException, FileNotFoundException, IOException
    {

        List<Student> newStudents = new ArrayList<>();

        File file = new File(fileName);
        boolean skip = true;

        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        while ((line = reader.readLine()) != null)
        {
            //skip blank line
            if(line.isEmpty())
                continue;
            //number of columns are 4
            String[] parts = line.split(",");

            if (parts.length != CSV_COLUMNS)
                throw new InvalidFileFormatException("Invalid File Format");
            if (skip)
            {
                skip = false;
                continue;
            }

            //validate line
            if (!validLine(parts))
                throw new InvalidFileFormatException("Invalid File Format");

            newStudents.add(new Student(parts[BANNERID],parts[LASTNAME],parts[FIRSTNAME],parts[EMAILID]));
        }

        return newStudents;
    }

    public boolean validLine(String[] parts)
    {
        //bannerid should start with B00 and remaining 6 characters should be digits

        //first name and last name should not be empty

        //use pattern matching to validate email id

        return validBannerId(parts[BANNERID]) &&
                validNames(parts[FIRSTNAME], parts[LASTNAME]) &&
                validEmailId(parts[EMAILID]);
    }

    public boolean validBannerId(String bannerId)
    {
        //remove whitespace
        bannerId = bannerId.trim();

        //9 characters long
        if (bannerId.length() != BANNERID_LENGTH)
        {
            System.out.println("Invalid Lenght of BannerID");
            return false;
        }

        //starts with B00
        if (!bannerId.substring(0, 3).equalsIgnoreCase("B00"))
        {
            System.out.println("BannerID not starting with B00");
            return false;
        }

        //remaining 6 are digits
        if (!Pattern.matches("\\d{6}", bannerId.substring(3)))
        {
            System.out.println("Invalid last 6 digits of BannerID");
            return false;
        }

        return true;
    }

    public boolean validNames(String firstname, String lastname)
    {

        if (firstname == null || firstname.trim().isEmpty())
        {
            System.out.println("Invalid Firstname");
            return false;
        }

        if (lastname == null || lastname.trim().isEmpty())
        {
            System.out.println("Invalid Lastname");
            return false;
        }

        return true;
    }

    public boolean validEmailId(String emailId)
    {
        emailId = emailId.trim();
        //match regular expression for email id
        //credit for regex: https://regexr.com/2rhq7
        if (!Pattern.matches("[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", emailId))
        {
            System.out.println("Invalid Email ID");
            return false;
        }

        return true;
    }
}
