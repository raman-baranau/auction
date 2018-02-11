package by.baranau.auction.command;

import by.baranau.auction.constant.AuctionConstant;
import by.baranau.auction.helper.ConfigurationManager;
import by.baranau.auction.helper.MessageManager;
import by.baranau.auction.receiver.AuctionReceiver;

public class DeleteAuctionCommand implements ActionCommand {
	
	private AuctionReceiver receiver = new AuctionReceiver();
	
	public String execute(SessionRequestContent request) {
		String auctionId = request.getParameter(AuctionConstant.PARAM_NAME_AUCTION_ID)[0]; 
		
		String page = ConfigurationManager.getProperty("path.page.auctiondelete");

		if (receiver.deleteAuction(Integer.parseInt(auctionId))) {
		    request.setRequestAttribute("msgDeleteSuccess",
                    MessageManager.getProperty("message.auction.deletesuccess"));
		} else {
			request.setRequestAttribute("msgDeleteFail",
					MessageManager.getProperty("message.auction.deletefail"));
		}
		
		return page;
	}
}
