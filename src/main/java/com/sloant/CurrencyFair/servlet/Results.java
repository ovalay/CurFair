package com.sloant.CurrencyFair.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sloant.CurrencyFair.MongoConnector;
import com.sloant.CurrencyFair.TradeMessage;

@WebServlet(urlPatterns = { "/results" })
public class Results extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		out.println("<html><head/><body>");
		out.println("<p>There are "
				+ MongoConnector.getInstance().countDocuments()
				+ " trade messages received</p>");

		if (req.getParameter("start") != null
				&& req.getParameter("limit") != null) {
			int start, limit;
			try {
				start = Integer.parseInt(req.getParameter("start"));
				limit = Integer.parseInt(req.getParameter("limit"));

				// quick check on valid inputs
				if (start < 0 || (limit < 1 || limit > 5000)) {
					out.println("Limit of 5000 messages displayed in table at a time");
					limit = 5000;
					start = 0;
				}
				List<TradeMessage> messages = MongoConnector.getInstance()
						.fetchMessages(start, limit);
				out.println("<table>");
				out.println("<tbody><tr><th>User ID</th><th>Currency From</th><th>Currency To</th><th>Amount Sell</th><th>Amount Buy</th><th>Rate</th><th>Time Placed</th><th>Originating Country</th></tr>");
				for (TradeMessage message : messages) {
					out.println("<tr>");
					out.println("<td>" + message.getUserId() + "</td><td>"
							+ message.getCurrencyFrom() + "</td><td>"
							+ message.getCurrencyTo() + "</td><td>"
							+ message.getAmountSell() + "</td><td>"
							+ message.getAmountBuy() + "</td><td>"
							+ message.getRate() + "</td><td>"
							+ message.getTimePlaced() + "</td><td>"
							+ message.getOriginatingCountry() + "</td>");
					out.println("</tr>");
				}
				out.println("</tbody><table>");
				out.println("</body></html>");
			} catch (NumberFormatException e) {
				out.println("Please use the url parameters \"start\" and \"limit\" to display the results you need");
			}
		} else {
			out.println("Please use the url parameters \"start\" and \"limit\" to display the results you need");
		}
	}

}
