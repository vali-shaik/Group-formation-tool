package dal.asd.catme.util;

public class PasswordRulesUtil 
{
	public static boolean MINIMUM_LENGTH_ENABLE=Boolean.parseBoolean(System.getenv("MINIMUM_LENGTH_ENABLE"));
	public static int MINIMUM_LENGTH=Integer.parseInt(System.getenv("MINIMUM_LENGTH")); 
	
	public static boolean MAXIMUM_LENGTH_ENABLE=Boolean.parseBoolean(System.getenv("MAXIMUM_LENGTH_ENABLE"));
	public static int MAXIMUM_LENGTH=Integer.parseInt(System.getenv("MAXIMUM_LENGTH")); 
	
	public static boolean MINIMUM_UPPER_CASE_ENABLE=Boolean.parseBoolean(System.getenv("MINIMUM_UPPER_CASE_ENABLE"));
	public static int MINIMUM_UPPER_CASE_LENGTH=Integer.parseInt(System.getenv("MINIMUM_UPPER_CASE_LENGTH")); 
	public static String MINIMUM_UPPER_CASE_REGEX=System.getenv("MINIMUM_UPPER_CASE_REGEX");
	
	public static boolean MINIMUM_LOWER_CASE_ENABLE=Boolean.parseBoolean(System.getenv("MINIMUM_LOWER_CASE_ENABLE"));
	public static int MINIMUM_LOWER_CASE_LENGTH=Integer.parseInt(System.getenv("MINIMUM_LOWER_CASE_LENGTH")); 
	public static String MINIMUM_LOWER_CASE_REGEX=System.getenv("MINIMUM_LOWER_CASE_REGEX");
	
	public static boolean MINIMUM_SYMBOL_SPECIAL_ENABLE=Boolean.parseBoolean(System.getenv("MINIMUM_SYMBOL_SPECIAL_ENABLE"));
	public static int MINIMUM_SYMBOL_SPECIAL_LENGTH=Integer.parseInt(System.getenv("MINIMUM_SYMBOL_SPECIAL_LENGTH")); 
	public static String MINIMUM_SYMBOL_SPECIAL_REGEX=System.getenv("MINIMUM_SYMBOL_SPECIAL_REGEX");
	
	public static boolean NOT_ALLOWED_CHARACTER_ENABLE=Boolean.parseBoolean(System.getenv("NOT_ALLOWED_CHARACTER_ENABLE"));
	public static String NOT_ALLOWED_CHARACTER_REGEX=System.getenv("NOT_ALLOWED_CHARACTER_REGEX");
	
	public static boolean PASSWORD_HISTORY_ENABLE=Boolean.parseBoolean(System.getenv("PASSWORD_HISTORY_ENABLE"));
	
	public static String PASSWORD_HISTORY_QUERY="SELECT * from UserPasswords where BannerId=";
	public static String PASSWORD_HISTORY_ORDER_QUERY="ORDER BY CreatedDate LIMIT 10";
	public static String PASSWORD_FIELD="password";
}
