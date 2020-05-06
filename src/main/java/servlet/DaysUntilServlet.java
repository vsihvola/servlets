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
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String y = req.getParameter("year");
        String m = req.getParameter("month");
        String d = req.getParameter("day");

        int year = Integer.parseInt(y);
        int month = Integer.parseInt(m);
        int day = Integer.parseInt(d);

        LocalDate today = LocalDate.now();
        LocalDate userDate = LocalDate.of(year, month, day);

        long days = ChronoUnit.DAYS.between(today, userDate);

        req.setAttribute("days", days);
        req.setAttribute("userDate", userDate);

        req.getRequestDispatcher("/WEB-INF/daysUntil.jsp").forward(req, resp);
    }
}
