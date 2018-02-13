package by.baranau.auction.command;

import by.baranau.auction.data.User;
import by.baranau.auction.helper.ConfigurationManager;
import by.baranau.auction.helper.MessageManager;
import by.baranau.auction.receiver.AccountReceiver;

public class ShowProfileCommand implements ActionCommand {
    
    private AccountReceiver receiver = new AccountReceiver(); 
    
    @Override
    public String execute(SessionRequestContent request) {
        
        User user = null;
        
        String[] userIdBuf = request.getParameter("userId");
        if ( userIdBuf != null ) {
            int userId = Integer.parseInt(userIdBuf[0]);
            user = receiver.findUserById(userId);
        } else if ( request.getSessionAttribute("user") != null ){
            user = (User) request.getSessionAttribute("user");
        } else {
            return ConfigurationManager.getProperty("path.page.login");
        }
        
        String page = null;
        if (user != null) {
            int count = receiver.findLotCount(user.getId()); 
            request.setRequestAttribute("lot_count", count);
            request.setRequestAttribute("client", user);
            page = ConfigurationManager.getProperty("path.page.user.profile");
        } else {
            request.setRequestAttribute("profileNotFound", 
                    MessageManager.getProperty("message.user.profile_not_found"));
            page = ConfigurationManager.getProperty("path.page.info");
        }
        return page;
    }
}
