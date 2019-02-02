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
import model.segnalazione.Tipologia;
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
                Utente utente = ((Utente) req.getSession()
                        .getAttribute("utente"));
                List<Segnalazione> segnalazioni = sbl
                        .getSegnalazioniEffettuate(utente);
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

        SegnalazioneBL sbl = new SegnalazioneBL();

        Tipologia tipologia = new Tipologia();
        tipologia.setId(Integer.parseInt(req.getParameter("field-type")));

        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setTitolo(req.getParameter("field-title"));
        segnalazione.setDescrizione(req.getParameter("field-descr"));
        segnalazione.setTipologia(tipologia);
        segnalazione
                .setAutore((Utente) req.getSession().getAttribute("utente"));

        try {
            boolean res = sbl.updateSegnalazione(segnalazione);
            //TODO Aggiungere controllo risultato res
        } catch (Exception e) {
            String msgError = "Si e' verificato un errore.";
            req.setAttribute("msgError", msgError);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/error.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
