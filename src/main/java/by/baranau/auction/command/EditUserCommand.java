package by.baranau.auction.command;

import by.baranau.auction.data.User;
import by.baranau.auction.helper.ConfigurationManager;
import by.baranau.auction.helper.MessageManager;
import by.baranau.auction.receiver.AccountReceiver;
import by.baranau.auction.validator.AuctionValidator;

public class EditUserCommand implements ActionCommand {

    private AccountReceiver receiver = new AccountReceiver();
    private AuctionValidator validator = new AuctionValidator();
    
    @Override
    public String execute(SessionRequestContent request) {
        
        if (request.getSessionAttribute("user") == null) {
            request.setRequestAttribute("msgSessionExpired", 
                    MessageManager.getProperty("message.sessionexpired"));
            request.invalidateSession();
            return ConfigurationManager.getProperty("path.page.info");
        }
        
        User user = (User) request.getSessionAttribute("user");
        String oldPassword = null;
        String newPassword = null;
        String confirmedNewPassword = null;
        String email = null;
        String phoneNumber = null;
        
        String[] oldPswBuf = request.getParameter("oldPassword");
        String[] newPswBuf = request.getParameter("newPassword");
        String[] confirmedPswBuf = request.getParameter("confirmedNewPassword");
        String[] emailBuf = request.getParameter("clientEmail");
        String[] phoneNumberBuf = request.getParameter("phoneNumber");
        
        if ( oldPswBuf != null
                && newPswBuf != null
                && confirmedPswBuf != null ) {
            oldPassword = oldPswBuf[0];
            newPassword = newPswBuf[0];
            confirmedNewPassword = confirmedPswBuf[0];
            
            if ( validator.validatePassword(newPassword, confirmedNewPassword) ) {
                if (receiver.updateUserPassword(user, oldPassword, newPassword)) {
                    request.setRequestAttribute("editUserSuccess", 
                        MessageManager.getProperty("message.user.editprofilepassword.success"));
                } else {
                    request.setRequestAttribute("editUserFail", 
                            MessageManager.getProperty(
                                    "message.user.editprofile.wrongoldpassword"));
                }
            } else {
                request.setRequestAttribute("editUserFail", 
                        MessageManager.getProperty("message.user.editprofilevalidation.fail"));
            }
        } else if (emailBuf != null
                    && phoneNumberBuf != null) {
            
            email = emailBuf[0];
            phoneNumber = phoneNumberBuf[0];
            if ( validator.validateEmail(email)
                    && validator.validatePhoneNumber(phoneNumber) ) {
                receiver.updateUserContacts(user,  email, phoneNumber);
                request.setRequestAttribute("editUserSuccess", 
                        MessageManager.getProperty("message.user.editprofilecontacts.success"));
            } else {
                request.setRequestAttribute("editUserFail", 
                        MessageManager.getProperty("message.user.editprofilevalidation.fail"));
            }
        } else {
            request.setRequestAttribute("editUserFail", 
                    MessageManager.getProperty("message.user.editprofile.fail"));
        }
        
        //request.setSessionAttribute("user", user);
        
        return ConfigurationManager.getProperty("path.page.user.profile_edit");
    }
}
