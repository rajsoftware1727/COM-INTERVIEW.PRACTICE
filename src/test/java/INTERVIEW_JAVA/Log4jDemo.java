package INTERVIEW_JAVA;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

public class Log4jDemo {
	
	@Test
	public static void logger()
	{
	Logger logger=Logger.getLogger(Log4jDemo.class.getName());
	PropertyConfigurator.configure("Log4j.properties");

	logger.info("info level");
	logger.error("error level");
	}

}
