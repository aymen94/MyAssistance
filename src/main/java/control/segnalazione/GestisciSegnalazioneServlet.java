/*
  Project: MyAssistance
  Author: TeamC
  Date: 05/01/2019
*/
package control.segnalazione;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.BasicServlet;
import model.segnalazione.SegnalazioneBL;
import model.utente.Gestore;
import model.utente.Utente;

import java.io.IOException;

/**
 * Servlet for handling a report.
 */
@WebServlet("/gestore/segnalazioni")
public final class GestisciSegnalazioneServlet extends BasicServlet {
    /**
     * doGet method.
     * @param req request parameter.
     * @param resp response parameter.
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        if (isUtenteLoggato(req, resp)) {
            Utente rUser;
            rUser = (Utente) req.getSession().getAttribute("utente");

            if (rUser instanceof Gestore) {
                SegnalazioneBL sbl = new SegnalazioneBL();
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

                            sbl.inoltraSegnalazione(
                                    codiceSegnalazione, idTecnico);
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

                            sbl.rifiutaSegnalazione(
                                    codiceSegnalazione, motivation);
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
