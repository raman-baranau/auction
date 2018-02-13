package by.baranau.auction.command;

import by.baranau.auction.data.UserType;
import by.baranau.auction.helper.ConfigurationManager;

public class LogoutCommand implements ActionCommand {

	public String execute(SessionRequestContent request) {
		String page = ConfigurationManager.getProperty("path.page.index");
		request.setSessionAttribute("role", UserType.GUEST.toString());
		request.setSessionAttribute("user", null);
		request.invalidateSession();
		return page;
	}
}
