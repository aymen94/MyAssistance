/*
  Project: MyAssistance
  Author: Aymen
  Date: 05/01/2019
*/
package control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet for redirecting to various index.
 */
@WebServlet("/logout")
public final class LogoutServlet extends HttpServlet {
    /**
     * doGet method.
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession(true).invalidate();
        resp.sendRedirect(getServletContext().getContextPath());
    }

    /**
     * doPost method.
     */
    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
