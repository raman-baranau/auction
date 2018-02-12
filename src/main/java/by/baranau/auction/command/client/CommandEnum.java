package by.baranau.auction.command.client;

import by.baranau.auction.command.ActionCommand;
import by.baranau.auction.command.CreateAuctionCommand;
import by.baranau.auction.command.DeleteAuctionCommand;
import by.baranau.auction.command.EditUserCommand;
import by.baranau.auction.command.LoginCommand;
import by.baranau.auction.command.LogoutCommand;
import by.baranau.auction.command.PlaceBidCommand;
import by.baranau.auction.command.RegisterCommand;
import by.baranau.auction.command.ShowAuctionCommand;
import by.baranau.auction.command.ShowAuctionsCommand;
import by.baranau.auction.command.ShowProfileCommand;

public enum CommandEnum {
	LOGIN (new LoginCommand()),
	LOGOUT (new LogoutCommand()),
	REGISTER (new RegisterCommand()),
	EDIT_USER(new EditUserCommand()),
	SHOW_PROFILE(new ShowProfileCommand()),
	CREATE_AUCTION(new CreateAuctionCommand()),
	DELETE_AUCTION(new DeleteAuctionCommand()),
	SHOW_AUCTION(new ShowAuctionCommand()),
	SHOW_AUCTIONS(new ShowAuctionsCommand()),
	PLACE_BID(new PlaceBidCommand());
	
	ActionCommand command;
	
	private CommandEnum(ActionCommand command) {
		this.command = command; 
	}
	
	public ActionCommand getCurrentCommand() {
		return command;
	}
}
