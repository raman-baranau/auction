package by.baranau.auction.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		session.setAttribute("role", "guest");
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub

	}

}
