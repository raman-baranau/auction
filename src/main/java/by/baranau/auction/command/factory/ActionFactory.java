package by.baranau.auction.command.factory;

import javax.servlet.http.HttpServletRequest;

import by.baranau.auction.command.ActionCommand;
import by.baranau.auction.command.client.CommandEnum;
import by.baranau.auction.helper.MessageManager;
public class ActionFactory {
	
	public ActionCommand defineCommand(HttpServletRequest request) {
		ActionCommand current = null;
		String action = request.getParameter("command");
		if (action == null || action.isEmpty()) {
			return current;
		}
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			request.setAttribute("wrongAction", action
					+ MessageManager.getProperty("message.wrongaction"));
		}
		return current;
	}
}
