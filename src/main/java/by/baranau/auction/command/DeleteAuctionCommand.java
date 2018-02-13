package by.baranau.auction.command;

import by.baranau.auction.constant.AuctionConstant;
import by.baranau.auction.data.User;
import by.baranau.auction.helper.ConfigurationManager;
import by.baranau.auction.helper.MessageManager;
import by.baranau.auction.receiver.AuctionReceiver;

public class DeleteAuctionCommand implements ActionCommand {
	
	private AuctionReceiver receiver = new AuctionReceiver();
	
	public String execute(SessionRequestContent request) {
	    
	    String page = ConfigurationManager.getProperty("path.page.auctiondelete");
	    
	    String[] auctionIdBuf = request.getParameter(AuctionConstant.PARAM_NAME_AUCTION_ID);
	    
	    User user = (User) request.getSessionAttribute("user");
	    
	    if (request.getSessionAttribute("user") == null) {
	        request.setRequestAttribute("msgDeleteFail", 
	                MessageManager.getProperty("message.sessionexpired"));
	        request.invalidateSession();
	        return page;
	    }
	    
	    if ( auctionIdBuf != null ) {
	        String auctionId = auctionIdBuf[0]; 
	        
    		if (receiver.deleteAuction(Integer.parseInt(auctionId), user)) {
    		    request.setRequestAttribute("msgDeleteSuccess",
                        MessageManager.getProperty("message.auction.deletesuccess"));
    		} 
	    } else {
            request.setRequestAttribute("msgDeleteFail",
                    MessageManager.getProperty("message.auction.deletefail"));
        }
		return page;
	}
}
