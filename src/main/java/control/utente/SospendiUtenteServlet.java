/*
  Project: MyAssistance
  Author: TeamC
  Date: 05/01/2019
*/
package control.utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.BasicServlet;
import model.utente.CSU;
import model.utente.Gestore;
import model.utente.Utente;
import model.utente.UtenteBL;

import java.io.IOException;

/**
 * Servlet for suspending an user.
 */
public final class SospendiUtenteServlet extends BasicServlet {
    /**
     * doGet method.
     */
    @Override protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        if (isUtenteLoggato(req, resp)) {

            Utente rUser;
            rUser = (Utente) req.getSession().getAttribute("utente");

            if (rUser instanceof Gestore) {

                int id = Integer.parseInt(req.getParameter("id"));

                CSU csu = null;
                UtenteBL ubl = new UtenteBL();

                csu = ubl.getById(id);
                boolean res = ubl.sospendiUtente(csu);

                if(res) {
                    //TODO reindirizzamento a pagina che indica l'avvenuta sospensione
                } else {
                    String msgError = "Si e' verificato un errore.";
                    req.setAttribute("msgError", msgError);
                    RequestDispatcher dispatcher =
                            getServletContext().getRequestDispatcher(
                                    "/error.jsp");
                    dispatcher.forward(req, resp);
                }

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
