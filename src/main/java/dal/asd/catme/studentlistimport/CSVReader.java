package dal.asd.catme.studentlistimport;

import dal.asd.catme.accesscontrol.Student;
import dal.asd.catme.exception.InvalidFileFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CSVReader implements ICSVReader
{
    static int BANNERID = 0;
    static int LASTNAME = 1;
    static int FIRSTNAME = 2;
    static int EMAILID = 3;

    static int BANNERID_LENGTH = 9;
    static int CSV_COLUMNS = 4;

    @Override
    public ArrayList<Student> readFile(InputStream inputStream) throws InvalidFileFormatException, FileNotFoundException, IOException
    {

        ArrayList<Student> newStudents = new ArrayList<>();

        boolean skip = true;

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null)
        {
            if (line.isEmpty())
                continue;
            String[] parts = line.split(",");

            if (parts.length < CSV_COLUMNS || parts.length > CSV_COLUMNS)
                throw new InvalidFileFormatException("Invalid File Format");
            if (skip)
            {
                skip = false;
                continue;
            }

            if (validLine(parts) == false)
                throw new InvalidFileFormatException("Invalid File Format");

            newStudents.add(new Student(parts[BANNERID], parts[LASTNAME], parts[FIRSTNAME], parts[EMAILID]));
        }

        return newStudents;
    }

    public boolean validLine(String[] parts)
    {
        return validBannerId(parts[BANNERID]) &&
                validNames(parts[FIRSTNAME], parts[LASTNAME]) &&
                validEmailId(parts[EMAILID]);
    }

    @Override
    public boolean validBannerId(String bannerId)
    {
        bannerId = bannerId.trim();

        if (bannerId.length() < BANNERID_LENGTH || bannerId.length()>BANNERID_LENGTH)
        {
            return false;
        }
        if (bannerId.substring(0, 3).equalsIgnoreCase("B00")==false)
        {
            return false;
        }

        if (Pattern.matches("\\d{6}", bannerId.substring(3))==false)
        {
            return false;
        }

        return true;
    }

    @Override
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

    @Override
    public boolean validEmailId(String emailId)
    {
        emailId = emailId.trim();
        //credit for regex: https://regexr.com/2rhq7
        if (Pattern.matches("[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", emailId))
        {
            return true;
        }

        return false;
    }

    @Override
    public void validateFile(MultipartFile file) throws InvalidFileFormatException
    {
        if (file.isEmpty())
        {
            throw new InvalidFileFormatException("Please Upload File");
        } else if (file.getSize() >= 10 * 1024 * 1024)
        {
            throw new InvalidFileFormatException("Please Upload File less than 10 mb");
        } else if (file.getContentType().equals("text/csv")==false)
        {
            throw new InvalidFileFormatException("Please Select CSV File");
        }
    }
}
