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
import model.segnalazione.Segnalazione;
import model.segnalazione.SegnalazioneBL;
import model.segnalazione.SegnalazioneDB;
import model.utente.Utente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet for updating a report.
 */
@WebServlet("/utente/segnalazioni")
public final class ModificaSegnalazioneServlet extends BasicServlet {
    /**
     * doGet method.
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        if (isUtenteLoggato(req, resp)) {
            SegnalazioneBL sbl = new SegnalazioneBL();
            try {
                List<Segnalazione> segnalazioni = sbl.getSegnalazioniEffettuate(
                        (Utente) req.getSession().getAttribute("utente"));
                req.setAttribute("segnalazioni", segnalazioni);
            } catch (Exception e) {
                req.setAttribute("segnalazioni", new ArrayList<Segnalazione>());
            }

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/utente/segnalazioni.jsp");
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

        String cod = (String) req.getParameter("cod");
        String descrizione = (String) req.getParameter("descrizione");

        if (cod != null && descrizione != null) {
            SegnalazioneBL sbl = new SegnalazioneBL();
            try {
                Segnalazione segnalazione = new SegnalazioneDB()
                        .getByCod(Integer.parseInt(cod));
                segnalazione.setDescrizione(descrizione);

                boolean res = sbl.updateSegnalazione(segnalazione);
                if (res) {
                    resp.sendRedirect("./");
                } else {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                String msgError = "Si è verificato un errore.";
                req.setAttribute("msgError", msgError);
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/error.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}
