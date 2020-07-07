package dal.asd.catme.studentlistimport;

import dal.asd.catme.accesscontrol.Student;
import dal.asd.catme.exception.InvalidFileFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public interface ICSVReader
{
    public ArrayList<Student> readFile(InputStream inputStream) throws InvalidFileFormatException, FileNotFoundException, IOException;

    public boolean validBannerId(String bannerId);

    public boolean validNames(String firstname, String lastname);

    public boolean validEmailId(String emailId);

    public void validateFile(MultipartFile file) throws InvalidFileFormatException;
}
