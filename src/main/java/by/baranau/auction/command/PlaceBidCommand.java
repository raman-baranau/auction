package by.baranau.auction.command;

import by.baranau.auction.data.User;
import by.baranau.auction.receiver.AuctionReceiver;

public class PlaceBidCommand implements ActionCommand {
    
    private AuctionReceiver receiver = new AuctionReceiver();
    
    @Override
    public String execute(SessionRequestContent request) {
        String page = null;
        //TODO
        User user = (User) request.getSessionAttribute("user");
        String sAuctionId = request.getParameter("auctionId")[0];
        int auctionId = Integer.parseInt(sAuctionId);
        String sBidValue = request.getParameter("bid")[0];
        double bidVlaue = Double.parseDouble(sBidValue);
        
        
        return page;
    }
}
