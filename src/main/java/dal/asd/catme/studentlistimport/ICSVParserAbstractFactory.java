package dal.asd.catme.studentlistimport;

import java.io.InputStream;

public interface ICSVParserAbstractFactory
{
    ICSVReader getCSVReader(InputStream inputStream);
    ICSVParser getCSVParser();
}
