package by.baranau.auction.command;

import by.baranau.auction.data.User;
import by.baranau.auction.data.UserType;
import by.baranau.auction.helper.ConfigurationManager;
import by.baranau.auction.helper.MessageManager;
import by.baranau.auction.receiver.AccountReceiver;

public class LoginCommand implements ActionCommand {
	
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private AccountReceiver receiver = new AccountReceiver();

	public String execute(SessionRequestContent request) {
		String page = null;
		String[] loginBuf = request.getParameter(PARAM_NAME_LOGIN);
		String[] passwordBuf = request.getParameter(PARAM_NAME_PASSWORD);
		
		if (loginBuf == null || passwordBuf == null) {
		    request.setRequestAttribute("msgInvalidParameter", 
                    MessageManager.getProperty("message.invalidparameter"));
            return ConfigurationManager.getProperty("path.page.info");
        }
		
		String login = loginBuf[0];
		String password = passwordBuf[0];
		
		User targetUser = receiver.findUserByCredentials(login, password);

		if (targetUser != null) {
			request.setSessionAttribute("user", targetUser);
			request.setSessionAttribute("role", UserType.CLIENT.toString());
			page = ConfigurationManager.getProperty("path.page.main");
		} else  {
			request.setRequestAttribute("errorLoginPassMessage",
					MessageManager.getProperty("message.loginerror"));
			page = ConfigurationManager.getProperty("path.page.login");
		}
		return page;
	}
}
