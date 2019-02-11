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
import model.utente.CSU;
import model.utente.Gestore;
import model.utente.Utente;
import model.utente.UtenteBL;

import java.io.IOException;

/**
 * Servlet for suspending an user.
 */
@WebServlet("/gestore/sospendiUtente")
public final class SospendiUtenteServlet extends BasicServlet {
    /**
     * doGet method.
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("./");
    }

    /**
     * doPost method.
     */
    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        if (isUtenteLoggato(req, resp, true)) {

            Utente rUser;
            rUser = (Utente) req.getSession().getAttribute("utente");

            if (rUser instanceof Gestore) {

                int id = Integer.parseInt(req.getParameter("id"));

                CSU csu = new CSU();
                csu.setId(id);
                UtenteBL ubl = new UtenteBL();

                boolean res;
                try {
                    res = ubl.sospendiUtente(csu);

                    if (res) {
                        resp.sendRedirect("./utenti");
                        return;
                    } else {
                        throw new RuntimeException();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    String msgError = "Si è verificato un errore.";
                    req.setAttribute("msgError", msgError);
                    RequestDispatcher dispatcher = getServletContext()
                            .getRequestDispatcher("/error.jsp");
                    dispatcher.forward(req, resp);
                }

            } else {
                resp.sendRedirect("../");
            }

        }
    }
}
