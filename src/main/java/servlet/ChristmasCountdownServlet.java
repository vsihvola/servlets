package servlet;

import static java.time.temporal.ChronoUnit.*;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/christmas")
public class ChristmasCountdownServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LocalDate today = LocalDate.now();
        LocalDate christmas = getNextChristmas(today);

        long daysBetween = DAYS.between(today, christmas);
        req.setAttribute("days", daysBetween);

        req.getRequestDispatcher("/WEB-INF/christmasCountdown.jsp").forward(req, resp);
    }

    private LocalDate getNextChristmas(LocalDate start) {
        LocalDate christmasThisYear = LocalDate.of(start.getYear(), 12, 24);
        if (start.isBefore(christmasThisYear)) {
            return christmasThisYear;
        } else {
            return christmasThisYear.plusYears(1);
        }
    }
}
