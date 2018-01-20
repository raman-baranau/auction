package by.baranau.auction.command;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
	String execute(HttpServletRequest request);
}