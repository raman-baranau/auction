package by.baranau.auction.validator;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class AuctionValidator {
	
	/**
	 * A password at least 8 characters length containing at least 1 lower case letter,
	 * 1 upper case letter and 1 special character
	 */
	public final static String PATTERN_PASSWORD = 
			"^(?=.*?\\p{Lu})(?=.*?\\p{Ll})(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
	
	/**
	 * A user name with length 8 to 20 containing letters and digits. Also can contain
	 * an underscore character but not at the beginning or at the end of string
	 */
	public final static String PATTERN_LOGIN =
			"^(?=.{8,20}$)(?![_])(?!.*[_]{2,})[a-zA-Z0-9_]+(?<![_])$";
	
	public final static String PATTERN_FIRST_NAME = 
			"^\\p{L}{2,44}$";
	
	public final static String PATTERN_LAST_NAME = 
			PATTERN_FIRST_NAME;
	
	public final static String PATTERN_EMAIL = 
			"\\A(?=[a-z0-9@.!#$%&'*+\\/=?^_`{|}~-]{6,254}\\z)"
			+ "(?=[a-z0-9.!#$%&'*+\\/=?^_`{|}~-]{1,64}@)"
			+ "[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+)*@"
			+ "(?:(?=[a-z0-9-]{1,63}\\.)[a-z0-9]"
			+ "(?:[a-z0-9-]*[a-z0-9])?\\.)+(?=[a-z0-9-]{1,63}\\z)"
			+ "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\z";
	
	public final static String PATTERN_PHONE_NUMBER =
			"";
	
	private final static int LOT_NAME_LENGTH = 80;
	
	private final static int LOT_DESCRIPTION_LENGTH = 255;
	
	public boolean validateRegisterForm(String login, String password, String confirmedPassword,
			String firstName, String lastName, String email, String phoneNumber){
		return Pattern.matches(PATTERN_LOGIN, login) &&
				Pattern.matches(PATTERN_FIRST_NAME, firstName) &&
				Pattern.matches(PATTERN_LAST_NAME, lastName) &&
				Pattern.matches(PATTERN_EMAIL, email) &&
				validatePassword(password, confirmedPassword) &&
				(phoneNumber == null || Pattern.matches(PATTERN_PHONE_NUMBER, phoneNumber));
	}
	
	public boolean validateAuction(LocalDateTime start, LocalDateTime end, String lotName,
			String lotDescription, String initialPrice) {
		double dInitialPrice = 0.;
		
		try {
			dInitialPrice = Double.parseDouble(initialPrice);
		} catch (NumberFormatException e) {
			return false;
		}
		
		return validateDate(start, end) 
				&& lotName.length() <= LOT_NAME_LENGTH
				&& lotDescription.length() <= LOT_DESCRIPTION_LENGTH
				&& dInitialPrice > 0;
	}
	
	public boolean validateDate(LocalDateTime start, LocalDateTime end) {
		return start.isBefore(end);
	}
	
	private boolean validatePassword(String password, String confirmed_password) {
		return (validatePassword(password) && password.equals(confirmed_password));
	}
	
	private boolean validatePassword(String password) {
		return Pattern.matches(PATTERN_PASSWORD, password);
	}
}
