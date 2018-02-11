package by.baranau.auction.command;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionRequestContent {
	
	private HashMap<String, String[]> requestParameters;
	private HashMap<String, Object> requestAttributes;
	private HashMap<String, Object> sessionAttributes;
	private HttpSession session;
	
	public SessionRequestContent(HttpServletRequest request) {
		requestParameters = new HashMap<String, String[]>();
		requestAttributes = new HashMap<String, Object>();
		sessionAttributes = new HashMap<String, Object>();
		session = request.getSession();
		extractValues(request);
	}

	private void extractValues(HttpServletRequest request) {
		String key = null;
		HttpSession session = request.getSession();
		Enumeration<String> enumerationSession = session.getAttributeNames();
		Enumeration<String> enumerationRequest = request.getAttributeNames();
		
		while (enumerationRequest.hasMoreElements()) {
			key = enumerationRequest.nextElement();
			requestAttributes.put(key, request.getAttribute(key));
		}
		
		while (enumerationSession.hasMoreElements()) {
			key = enumerationSession.nextElement();
			sessionAttributes.put(key, session.getAttribute(key));
		}
		
		requestParameters = new HashMap<String, String[]>(request.getParameterMap());
	}
	
	public void insertValues(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Set<String> requestKeySet = requestAttributes.keySet();
		
		for (String requestKey : requestKeySet) {
			request.setAttribute(requestKey, requestAttributes.get(requestKey));
		}
		
		Set<String> sessionKeySet = sessionAttributes.keySet();
		
		for (String sessionKey : sessionKeySet) {
			session.setAttribute(sessionKey, sessionAttributes.get(sessionKey));
		}
		
		requestAttributes.clear();
		requestParameters.clear();
		sessionAttributes.clear();
	}
	
	public void invalidateSession(){
		session.invalidate();
	}
	
	public String[] getParameter(String key) {
		String[] value = requestParameters.get(key);
		requestParameters.remove(key);
		return value;
	}
	
	public void setParameter(String key, String[] param) {
		requestParameters.put(key, param);
	}
	
	public Object getRequestAttribute(String key) {
		Object value = requestAttributes.get(key);
		requestAttributes.remove(key);
		return value;
	}
	
	public void setRequestAttribute(String key, Object value) {
		requestAttributes.put(key, value);
	}
	
	public Object getSessionAttribute(String key) {
		Object value = sessionAttributes.get(key);
		sessionAttributes.remove(key);
		return value;
	}
	
	public void setSessionAttribute(String key, Object value) {
		sessionAttributes.put(key, value);
	}
}
