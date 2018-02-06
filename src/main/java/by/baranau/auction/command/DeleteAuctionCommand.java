package by.baranau.auction.command;

import by.baranau.auction.helper.ConfigurationManager;
import by.baranau.auction.helper.MessageManager;
import by.baranau.auction.receiver.AuctionReceiver;

public class DeleteAuctionCommand implements ActionCommand {
	
	private AuctionReceiver receiver = new AuctionReceiver();
	
	public String execute(SessionRequestContent request) {
		String auctionId = (String)request.getRequestAttribute("auction_id"); 
		
		String page = null;

		if (receiver.deleteAuction(Integer.parseInt(auctionId))) {
			page = ConfigurationManager.getProperty("path.page.auctiondeleted");
		} else {
			request.setRequestAttribute("errorDeleteAuctionMessage",
					MessageManager.getProperty("message.auction.deleteerror"));
			page = ConfigurationManager.getProperty("path.page.auctiondelete");
		}
		
		return page;
	}
}
