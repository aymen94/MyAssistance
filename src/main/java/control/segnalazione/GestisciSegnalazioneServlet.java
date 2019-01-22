/*
  Project: MyAssistance
  Author: TeamC
  Date: 05/01/2019
*/
package control.segnalazione;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.segnalazione.SegnalazioneBL;
import model.segnalazione.SegnalazioneDB;
import model.utente.Utente;

import java.io.IOException;

/**
 * Servlet for handling a report.
 */
public class GestisciSegnalazioneServlet extends HttpServlet {
    /**
     * doGet method.
     */
    @Override protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        Utente rUser;

        rUser = (Utente) req.getSession().getAttribute("utente");

        if (rUser == null) {
            req.getSession().invalidate();
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(
                            "/index.jsp");
            dispatcher.forward(req, resp);
        } else {
            SegnalazioneDB sdb = new SegnalazioneDB();
            SegnalazioneBL sbl = new SegnalazioneBL(sdb);
            String operation = null, motivation;
            int codiceSegnalazione, idTecnico;
            operation = req.getParameter("op");

            if (operation != null) {
                if (operation.equalsIgnoreCase("inoltra")) {
                    try {
                        codiceSegnalazione = Integer.parseInt(
                                req.getParameter("codice"));
                        idTecnico = Integer.parseInt(
                                req.getParameter("ufficio"));

                        sbl.inoltraSegnalazione(codiceSegnalazione, idTecnico);
                    } catch (Exception e) {
                        String msgError = "Si e' verificato un errore.";
                        req.setAttribute("msgError", msgError);
                        RequestDispatcher dispatcher =
                                getServletContext().getRequestDispatcher(
                                        "/error.jsp");
                        dispatcher.forward(req, resp);
                    }
                } else if (operation.equalsIgnoreCase("rifiuta")) {
                    try {
                        codiceSegnalazione = Integer.parseInt(
                                req.getParameter("codice"));
                        motivation = req.getParameter("motivation");

                        sbl.rifiutaSegnalazione(codiceSegnalazione, motivation);
                    } catch (Exception e) {
                        String msgError = "Si e' verificato un errore.";
                        req.setAttribute("msgError", msgError);
                        RequestDispatcher dispatcher =
                                getServletContext().getRequestDispatcher(
                                        "/error.jsp");
                        dispatcher.forward(req, resp);
                    }
                } else if (operation.equalsIgnoreCase("risolvi")) {
                    try {
                        codiceSegnalazione = Integer.parseInt(
                                req.getParameter("codice"));

                        sbl.segnaRisolta(codiceSegnalazione);
                    } catch (Exception e) {
                        String msgError = "Si e' verificato un errore.";
                        req.setAttribute("msgError", msgError);
                        RequestDispatcher dispatcher =
                                getServletContext().getRequestDispatcher(
                                        "/error.jsp");
                        dispatcher.forward(req, resp);
                    }
                }
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
