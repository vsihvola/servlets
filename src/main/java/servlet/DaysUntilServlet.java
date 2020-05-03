package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/daysUntil")
public class DaysUntilServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int year = Integer.parseInt(req.getParameter("year"));
		int month = Integer.parseInt(req.getParameter("month"));
		int day = Integer.parseInt(req.getParameter("day"));

		long daysLeft = GetDay(year, month, day);

		req.setAttribute("DaysUntilDate", daysLeft);
		req.setAttribute("year", year);
		req.setAttribute("month", month);
		req.setAttribute("days", day);

		// forward the request to the index.jsp page
		req.getRequestDispatcher("/WEB-INF/daysUntil.jsp").forward(req, resp);

	}

	public static long GetDay(int year, int month, int day) {

		LocalDate Today = LocalDate.now();
		LocalDate theDay = LocalDate.of(year, month, day);

		long daysLeft = ChronoUnit.DAYS.between(Today, theDay);

		return daysLeft;

	}

}
