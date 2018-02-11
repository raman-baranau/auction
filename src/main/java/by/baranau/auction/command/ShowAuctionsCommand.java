package by.baranau.auction.command;

import java.util.List;

import by.baranau.auction.data.Auction;
import by.baranau.auction.helper.ConfigurationManager;
import by.baranau.auction.helper.MessageManager;
import by.baranau.auction.receiver.AuctionReceiver;

public class ShowAuctionsCommand implements ActionCommand {
	
	private AuctionReceiver receiver = new AuctionReceiver();
	
	public String execute(SessionRequestContent request) {
		List<Auction> auctions = receiver.findAuctions();
		
		String page = ConfigurationManager.getProperty("path.page.auctionlist");
		if (!auctions.isEmpty()){
			request.setRequestAttribute("auctions", auctions);
		} else {
			request.setRequestAttribute("emptyAuctionList",
					MessageManager.getProperty("message.auction.emptylist"));
		}
		
		return page;
	}
}
