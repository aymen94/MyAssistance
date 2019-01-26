/*
  Project: MyAssistance
  Author: TeamC
  Date: 05/01/2019
*/
package control.utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet per la lista utenti.
 */
@WebServlet("/gestore/utenti")
public final class UtentiServlet extends HttpServlet {
    /**
     *
     */
    @Override protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(
                        "/gestore/utenti.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     *
     */
    @Override protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
