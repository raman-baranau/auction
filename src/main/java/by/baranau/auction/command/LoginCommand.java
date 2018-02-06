package by.baranau.auction.command;

import by.baranau.auction.helper.ConfigurationManager;
import by.baranau.auction.helper.MessageManager;
import by.baranau.auction.receiver.AccountReceiver;

public class LoginCommand implements ActionCommand {
	
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private AccountReceiver receiver = new AccountReceiver();

	public String execute(SessionRequestContent request) {
		String page = null;
		String login = request.getParameter(PARAM_NAME_LOGIN)[0];
		String password = request.getParameter(PARAM_NAME_PASSWORD)[0];
		Boolean checkResult = false;
		
		checkResult = receiver.checkLogin(login, password);

		if (checkResult) {
			request.setSessionAttribute("user", login);
			page = ConfigurationManager.getProperty("path.page.main");
		} else  {
			request.setRequestAttribute("errorLoginPassMessage",
					MessageManager.getProperty("message.loginerror"));
			page = ConfigurationManager.getProperty("path.page.login");
		}
		return page;
	}
}
