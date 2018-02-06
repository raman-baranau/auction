package by.baranau.auction.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.baranau.auction.command.ActionCommand;
import by.baranau.auction.command.SessionRequestContent;
import by.baranau.auction.command.factory.ActionFactory;
import by.baranau.auction.helper.ConfigurationManager;
import by.baranau.auction.helper.MessageManager;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	public void Service(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		String page = null;
		ActionFactory client = new ActionFactory();
		SessionRequestContent content = new SessionRequestContent(request);
		ActionCommand command = client.defineCommand(content);
		if (command != null) {
			page = command.execute(content);
			if (page != null) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
				dispatcher.forward(request, response);
			} else  {
				page = ConfigurationManager.getProperty("path.page.index");
				request.getSession().setAttribute("nullPage",
						MessageManager.getProperty("message.nullpage"));
				response.sendRedirect(request.getContextPath() + page );
			}
		}
	}
}
