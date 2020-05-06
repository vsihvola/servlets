package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sum")
public class SumServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // välitetään pyyntö edelleen sivulle form.jsp
        req.getRequestDispatcher("/WEB-INF/sum/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int a = Integer.parseInt(req.getParameter("a")); // 2
        int b = Integer.parseInt(req.getParameter("b")); // 3

        // servletiltä voidaan välittää Java-arvoja JSP-sivulle _attribuutteina_
        req.setAttribute("first", a);
        req.setAttribute("second", b);

        // välitetään pyyntö eteenpäin result.jsp-sivulle:
        req.getRequestDispatcher("/WEB-INF/sum/result.jsp").forward(req, resp);
    }
}
