package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/christmas")
public class ChristmasCountdownServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		long daysLeft = GetDay();
		// resp.getWriter().println("Days until Christmas " + daysLeft);
		// pass the time string to the JSP page as an attribute
		req.setAttribute("untilChristmas", daysLeft);

		// forward the request to the index.jsp page
		req.getRequestDispatcher("/WEB-INF/ChristmasCountdown.jsp").forward(req, resp);
	}

	public static long GetDay() {

		LocalDate Today = LocalDate.now();
		LocalDate Christmas = LocalDate.of(2020, 12, 24);

		long daysLeft = ChronoUnit.DAYS.between(Today, Christmas);

		return daysLeft;

	}

}
