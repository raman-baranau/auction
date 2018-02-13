package by.baranau.auction.command;

import by.baranau.auction.data.User;
import by.baranau.auction.helper.ConfigurationManager;
import by.baranau.auction.helper.MessageManager;
import by.baranau.auction.receiver.AuctionReceiver;

public class PlaceBidCommand implements ActionCommand {
    
    private AuctionReceiver receiver = new AuctionReceiver();
    
    @Override
    public String execute(SessionRequestContent request) {
        String page = null;

        User user = (User) request.getSessionAttribute("user");
        
        if (user == null) {
            request.setRequestAttribute("msgSessionExpired", 
                    MessageManager.getProperty("message.sessionexpired"));
            request.invalidateSession();
            return ConfigurationManager.getProperty("path.page.info");
        }
        
        String[] auctionIdBuf = request.getParameter("auctionId");
        String[] bidValueBuf = request.getParameter("bid");
        
        if (auctionIdBuf == null || bidValueBuf == null) {
            request.setRequestAttribute("msgInvalidParameter", 
                    MessageManager.getProperty("message.invalidparameter"));
            return ConfigurationManager.getProperty("path.page.info");
        }
        
        String sAuctionId = auctionIdBuf[0];
        int auctionId = Integer.parseInt(sAuctionId);
        String sBidValue = bidValueBuf[0];
        double bidValue = Double.parseDouble(sBidValue);
        
        page = ConfigurationManager.getProperty("path.page.info");
        if (receiver.makeBid(bidValue, user.getId(), auctionId)) {
            request.setRequestAttribute("msgBidSuccess", 
                    MessageManager.getProperty("message.bid.success"));
        } else {
            request.setRequestAttribute("msgBidFail", 
                    MessageManager.getProperty("message.bid.fail"));
        }
        
        return page;
    }
}
