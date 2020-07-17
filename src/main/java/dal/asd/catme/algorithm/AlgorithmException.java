package dal.asd.catme.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlgorithmException extends Exception
{

    private static final Logger log = LoggerFactory.getLogger(AlgorithmException.class);

    public AlgorithmException(String message)
    {
        super(message);
        log.error("Unable to process : " + message);
    }

}
