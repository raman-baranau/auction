package by.baranau.auction.command.client;

import by.baranau.auction.command.ActionCommand;
import by.baranau.auction.command.LoginCommand;
import by.baranau.auction.command.LogoutCommand;

public enum CommandEnum {
	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	};
	
	ActionCommand command;
	public ActionCommand getCurrentCommand() {
		return command;
	}
}
