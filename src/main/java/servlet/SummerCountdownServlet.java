package servlet;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/countdown")
public class SummerCountdownServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.of(2020, 6, 1, 0, 0);

        Duration duration = Duration.between(now, end);

        req.setAttribute("duration", duration);
        req.setAttribute("days", duration.toDays());
        req.setAttribute("hours", duration.toHoursPart());
        req.setAttribute("minutes", duration.toMinutesPart());
        req.setAttribute("seconds", duration.toSecondsPart());

        req.getRequestDispatcher("/WEB-INF/countdown.jsp").forward(req, resp);
    }
}
