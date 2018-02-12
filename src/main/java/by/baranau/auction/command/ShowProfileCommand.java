package by.baranau.auction.command;

import by.baranau.auction.data.User;
import by.baranau.auction.helper.ConfigurationManager;
import by.baranau.auction.helper.MessageManager;
import by.baranau.auction.receiver.AccountReceiver;

public class ShowProfileCommand implements ActionCommand {
    
    private AccountReceiver receiver = new AccountReceiver(); 
    
    @SuppressWarnings("unused")
    @Override
    public String execute(SessionRequestContent request) {
        
        User user = null;
        
        if (request.getParameter("userId") != null) {
            int userId = Integer.parseInt(request.getParameter("userId")[0]);
            user = receiver.findUserById(userId);
        } else {
            user = (User) request.getSessionAttribute("user");
        }
        
        int count = receiver.findLotCount(user.getId()); 
                
        String page = null;
        if (user != null) {
            request.setRequestAttribute("lot_count", count);
            request.setRequestAttribute("client", user);
            page = ConfigurationManager.getProperty("path.page.user.profile");
        } else {
            request.setRequestAttribute("profileNotFound", 
                    MessageManager.getProperty("message.user.profile_not_found"));
            page = ConfigurationManager.getProperty("path.page.user.profile_not_found");
        }
        return page;
    }
}
