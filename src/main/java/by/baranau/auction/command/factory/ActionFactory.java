package by.baranau.auction.command.factory;

import by.baranau.auction.command.ActionCommand;
import by.baranau.auction.command.SessionRequestContent;
import by.baranau.auction.command.client.CommandEnum;
import by.baranau.auction.helper.MessageManager;
public class ActionFactory {
	
	public ActionCommand defineCommand(SessionRequestContent request) {
		ActionCommand current = null;
		String action = null;
		String[] param = request.getParameter("command");
		
		if (param != null){
			action = param[0];
		} else {
			return current;
		}
		
		if (action == null || action.isEmpty()) {
			return current;
		}
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			request.setSessionAttribute("wrongAction", action
					+ MessageManager.getProperty("message.wrongaction"));
		}
		return current;
	}
}
