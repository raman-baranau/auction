package by.baranau.auction.command;

import by.baranau.auction.data.User;
import by.baranau.auction.helper.ConfigurationManager;
import by.baranau.auction.helper.MessageManager;
import by.baranau.auction.receiver.AccountReceiver;
import by.baranau.auction.validator.AuctionValidator;

public class RegisterCommand implements ActionCommand{
	
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static final String PARAM_NAME_CONFIRMED_PASSWORD = "confirmed_password";
	private static final String PARAM_NAME_FIRST_NAME = "first_name";
	private static final String PARAM_NAME_LAST_NAME = "last_name";
	private static final String PARAM_NAME_EMAIL = "client_email";
	private static final String PARAM_NAME_PHONE_NUMBER = "phone_number";
	
	private AccountReceiver receiver = new AccountReceiver();
	private AuctionValidator auctionValidator = new AuctionValidator();
	
	public String execute(SessionRequestContent request) {
		String page = null;
		String login = request.getParameter(PARAM_NAME_LOGIN)[0];
		String password = request.getParameter(PARAM_NAME_PASSWORD)[0];
		String confirmedPassword = request.getParameter(PARAM_NAME_CONFIRMED_PASSWORD)[0];
		String firstName = request.getParameter(PARAM_NAME_FIRST_NAME)[0];
		String lastName = request.getParameter(PARAM_NAME_LAST_NAME)[0];
		String email = request.getParameter(PARAM_NAME_EMAIL)[0];
		String phoneNumber = request.getParameter(PARAM_NAME_PHONE_NUMBER)[0];
		
		if (auctionValidator.validateRegisterForm(login, password, confirmedPassword,
				firstName, lastName, email, phoneNumber)) {
			User user = new User();
			user.setLogin(login);
			user.setPassword(password);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPhoneNumber(phoneNumber);
			receiver.registerUser(user);
			
			page = ConfigurationManager.getProperty("path.page.verifyemail");
		} else  {
			request.setSessionAttribute("errorRegisterMessage",
					MessageManager.getProperty("message.incorrectinput"));
			page = ConfigurationManager.getProperty("path.page.register");
		}
		return page;
	}
}
