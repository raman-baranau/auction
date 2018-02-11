package by.baranau.auction.command;

import java.time.LocalDateTime;

import by.baranau.auction.constant.AuctionConstant;
import by.baranau.auction.data.Auction;
import by.baranau.auction.data.AuctionType;
import by.baranau.auction.data.User;
import by.baranau.auction.helper.ConfigurationManager;
import by.baranau.auction.helper.MessageManager;
import by.baranau.auction.receiver.AuctionReceiver;
import by.baranau.auction.validator.AuctionValidator;

public class CreateAuctionCommand implements ActionCommand{

    private AuctionReceiver receiver = new AuctionReceiver();
	private AuctionValidator validator = new AuctionValidator(); 
	
	public String execute(SessionRequestContent request) {
		
		String durationDays = request.getParameter(
		        AuctionConstant.PARAM_NAME_DAYS_DURATION)[0];
		
		String durationHours = request.getParameter(
		        AuctionConstant.PARAM_NAME_HOURS_DURATION)[0];
		
		String lotName = request.getParameter(
		        AuctionConstant.PARAM_NAME_LOT_NAME)[0];
		
		String lotDescription = request.getParameter(
		        AuctionConstant.PARAM_NAME_LOT_DESCRIPTION)[0];
		
		String initialPrice = request.getParameter(
		        AuctionConstant.PARAM_NAME_INITIAL_PRICE)[0];
		
		User owner = (User) request.getSessionAttribute(
		        AuctionConstant.PARAM_NAME_USER);
		
		String auctionType = request.getParameter(
		        AuctionConstant.PARAM_NAME_AUCTION_TYPE)[0];
		
		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = null;
		
		String page = null;
		if ( validator.validateAuction(durationDays,
		        durationHours, lotName, lotDescription, initialPrice, auctionType) ) {
			Auction auction = new Auction();
			auction.setLotName(lotName);
			auction.setLotDescription(lotDescription);
			auction.setStartDate(start);
			int iDays = Integer.parseInt(durationDays);
			int iHours = Integer.parseInt(durationHours);
			end = start.plusDays(iDays).plusHours(iHours);
			auction.setEndDate(end);
			auction.setOwner(owner);
			auction.setAuctionType(AuctionType.valueOf(auctionType.toUpperCase()));
			auction.setInitialPrice(Double.parseDouble(initialPrice));
			
			receiver.createAuction(auction);
			page = ConfigurationManager.getProperty("path.page.auctioncreated");
		} else {
			request.setRequestAttribute("errorValidateAuctionMessage",
					MessageManager.getProperty("message.incorrectinput"));
			page = ConfigurationManager.getProperty("path.page.auctioncreate");
		}
		
		return page;
	}
}
