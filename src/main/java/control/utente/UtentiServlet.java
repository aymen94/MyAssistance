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
import model.utente.Gestore;
import model.utente.Utente;
import model.utente.UtenteBL;

import java.io.IOException;
import java.util.List;

/**
 * Servlet for getting a list of all the users registered.
 */
@WebServlet("/gestore/utenti")
public final class UtentiServlet extends BasicServlet {
    /**
     * ddoGet method.
     */
    @Override protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        List<Utente> users = null;

        if (isUtenteLoggato(req, resp)) {

            Utente rUser;
            rUser = (Utente) req.getSession().getAttribute("utente");

            if (rUser instanceof Gestore) {

                UtenteBL ubl = new UtenteBL();
                try {
                    users = ubl.getUtentiRegistrati();
                } catch (Exception e) {
                    String msgError = "Si e' verificato un errore.";
                    req.setAttribute("msgError", msgError);
                    RequestDispatcher dispatcher =
                            getServletContext().getRequestDispatcher(
                                    "/error.jsp");
                    dispatcher.forward(req, resp);
                }

                req.setAttribute("users", users);
                RequestDispatcher dispatcher =
                        getServletContext().getRequestDispatcher(
                                "/gestore/utenti.jsp");
                dispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("../");
            }

        }
    }

    /**
     * doPost method.
     */
    @Override protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
