
package dal.asd.catme.studentlistimport;

import dal.asd.catme.exception.InvalidFileFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CSVReader implements ICSVReader
{

    InputStream inputStream;

    public CSVReader(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    @Override
    public ArrayList<String> readFile() throws InvalidFileFormatException, IOException
    {

        ArrayList<String> lines = new ArrayList<>();

        boolean skip = true;

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null)
        {
            if (line.isEmpty())
                continue;

            if (skip)
            {
                skip = false;
                continue;
            }

            lines.add(line);

        }

        return lines;
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
        } else if (file.getContentType().equals("text/csv") == false)
        {
            throw new InvalidFileFormatException("Please Select CSV File");
        }
    }
}
