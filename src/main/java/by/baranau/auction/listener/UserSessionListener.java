package by.baranau.auction.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import by.baranau.auction.data.UserType;

public class UserSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		session.setAttribute("role", UserType.GUEST.toString());
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub

	}

}
