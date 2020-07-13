package dal.asd.catme.studentlistimport;

import java.io.InputStream;

public class CSVParserAbstractFactoryImpl implements ICSVParserAbstractFactory
{
    static ICSVParserAbstractFactory icsvParserAbstractFactory = null;
    private ICSVParser icsvParser;

    public CSVParserAbstractFactoryImpl()
    {
        icsvParser = new CSVParserImpl();
    }

    public static ICSVParserAbstractFactory instance()
    {
        if(icsvParserAbstractFactory==null)
        {
            icsvParserAbstractFactory = new CSVParserAbstractFactoryImpl();
        }
        return icsvParserAbstractFactory;
    }

    @Override
    public ICSVReader makeCSVReader(InputStream inputStream)
    {
        return new CSVReader(inputStream);
    }

    @Override
    public ICSVParser makeCSVParser()
    {
        return icsvParser;
    }
}
