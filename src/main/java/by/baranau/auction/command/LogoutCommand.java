package by.baranau.auction.command;

import javax.servlet.http.HttpServletRequest;

import by.baranau.auction.helper.ConfigurationManager;

public class LogoutCommand implements ActionCommand {

	public String execute(SessionRequestContent request) {
		String page = ConfigurationManager.getProperty("path.page.index");
		//request.getSession().invalidate();
		return page;
	}

}
