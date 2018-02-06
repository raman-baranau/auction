package by.baranau.auction.command;

import java.time.LocalDateTime;

import by.baranau.auction.data.Auction;
import by.baranau.auction.data.AuctionType;
import by.baranau.auction.helper.ConfigurationManager;
import by.baranau.auction.helper.MessageManager;
import by.baranau.auction.receiver.AuctionReceiver;
import by.baranau.auction.validator.AuctionValidator;

public class CreateAuctionCommand implements ActionCommand{
	
	private AuctionReceiver receiver = new AuctionReceiver();
	private AuctionValidator validator = new AuctionValidator(); 
	
	public String execute(SessionRequestContent request) {
		
		String endDate = request.getParameter("end_date")[0];
		String endTime = request.getParameter("end_time")[0];
		String lotName = request.getParameter("lot_name")[0];
		String lotDescription = request.getParameter("lot_description")[0];
		String initialPrice = request.getParameter("initial_price")[0];
		String ownerId = (String) request.getSessionAttribute("client_id");
		String auctionType = request.getParameter("auction_type")[0];
		
		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = LocalDateTime.parse(endDate + "T" + endTime);
		
		String page = null;
		if (validator.validateAuction(start, end, lotName, lotDescription, initialPrice)) {
			Auction auction = new Auction();
			auction.setLotName(lotName);
			auction.setLotDescription(lotDescription);
			auction.setStartDate(start);
			auction.setEndDate(end);
			auction.setOwnerId(Integer.parseInt(ownerId));
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
