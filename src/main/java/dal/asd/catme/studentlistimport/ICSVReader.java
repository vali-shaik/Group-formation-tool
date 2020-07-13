package dal.asd.catme.studentlistimport;

import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.InvalidFileFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public interface ICSVReader
{
    public ArrayList<String> readFile() throws InvalidFileFormatException, FileNotFoundException, IOException;

    public void validateFile(MultipartFile file) throws InvalidFileFormatException;
}
