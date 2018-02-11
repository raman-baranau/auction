package by.baranau.auction.command;

import by.baranau.auction.constant.AuctionConstant;
import by.baranau.auction.data.Auction;
import by.baranau.auction.helper.ConfigurationManager;
import by.baranau.auction.helper.MessageManager;
import by.baranau.auction.receiver.AuctionReceiver;

public class ShowAuctionCommand implements ActionCommand{
	
	private AuctionReceiver receiver = new AuctionReceiver();
	
	public String execute(SessionRequestContent request) {
		String auctionId = request.getParameter(AuctionConstant.PARAM_NAME_AUCTION_ID)[0]; 
		
		String page = ConfigurationManager.getProperty("path.page.auctioninfo");
		
		Auction auction = receiver.findAuction(Integer.parseInt(auctionId));
		if (auction != null) {
			request.setRequestAttribute("auction", auction);
		} else {
			request.setRequestAttribute("errorFindAuctionMessage",
					MessageManager.getProperty("message.auction.notfound"));
		}
		return page;
	}
}
