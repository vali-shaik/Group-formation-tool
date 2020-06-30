package dal.asd.catme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//Excluding the default Data source Connection
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class CatmeSpringProjectApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(CatmeSpringProjectApplication.class, args);
    }


}
