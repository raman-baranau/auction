package by.baranau.auction.command;

import by.baranau.auction.data.Auction;
import by.baranau.auction.helper.ConfigurationManager;
import by.baranau.auction.helper.MessageManager;
import by.baranau.auction.receiver.AuctionReceiver;

public class ShowAuctionCommand implements ActionCommand{
	
	private AuctionReceiver receiver = new AuctionReceiver();
	
	public String execute(SessionRequestContent request) {
		String auctionId = (String)request.getRequestAttribute("auction_id"); 
		
		String page = null;
		
		Auction auction = receiver.findAuction(Integer.parseInt(auctionId));
		if (auction != null) {
			request.setRequestAttribute("lot_name", auction.getLotName());
			request.setRequestAttribute("lot_description", auction.getLotDescription());
			request.setRequestAttribute("start_date", auction.getStartDate());
			request.setRequestAttribute("end_date", auction.getEndDate());
			request.setRequestAttribute("initial_price", auction.getInitialPrice());
			request.setRequestAttribute("last_price", auction.getSellingPrice());
			request.setRequestAttribute("buyer_id", auction.getClientId());
			request.setRequestAttribute("owner_id", auction.getOwnerId());
			request.setRequestAttribute("auction_type", auction.getAuctionType());
			page = ConfigurationManager.getProperty("path.page.auctioninfo");
		} else {
			request.setRequestAttribute("errorFindAuctionMessage",
					MessageManager.getProperty("message.auction.notfound"));
			page = ConfigurationManager.getProperty("path.page.auctionontfound");
		}
		return page;
	}
}
