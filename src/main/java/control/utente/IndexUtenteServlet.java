/*
  Project: MyAssistance
  Author: TeamC
  Date: 05/01/2019
*/
package control.utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.BasicServlet;

import java.io.IOException;

/**
 * Servlet for redirecting to the index of the user.
 */
@WebServlet("/utente/")
public final class IndexUtenteServlet extends BasicServlet {
    /**
     * doGet method.
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        if (isUtenteLoggato(req, resp)) {
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/utente/index.jsp");
            dispatcher.forward(req, resp);
        }
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
