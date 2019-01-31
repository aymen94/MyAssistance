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
import model.segnalazione.Tipologia;
import model.segnalazione.TipologiaDB;
import model.utente.Utente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet to make a reporting.
 */
@WebServlet("/utente/effettuaSegnalazione")
public final class EffettuaSegnalazioneServlet extends BasicServlet {
    /**
     * doGet method.
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        if (isUtenteLoggato(req, resp)) {
            TipologiaDB tdb = new TipologiaDB();
            try {
                List<Tipologia> tipologie = tdb.getAll();
                req.setAttribute("tipologie", tipologie);
            } catch (Exception e) {
                req.setAttribute("tipologie", new ArrayList<Tipologia>());
            }

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/utente/creaSegnalazione.jsp");
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

        if (isUtenteLoggato(req, resp)) {
            SegnalazioneDB sdb = new SegnalazioneDB();
            SegnalazioneBL sbl = new SegnalazioneBL();

            Tipologia tipologia = new Tipologia();
            tipologia.setId(Integer.parseInt(req.getParameter("field-type")));

            Segnalazione segnalazione = new Segnalazione();
            segnalazione.setTitolo(req.getParameter("field-title"));
            segnalazione.setDescrizione(req.getParameter("field-descr"));
            segnalazione.setTipologia(tipologia);
            segnalazione.setAutore(
                    (Utente) req.getSession().getAttribute("utente"));

            try {
                if (sbl.insertSegnalazione(segnalazione)) {
                    req.setAttribute("inserita", true);
                    doGet(req, resp);
                } else {
                    throw new RuntimeException("Segnalazione non inserita");
                }
            } catch (Exception e) {
                String msgError = "Si è verificato un errore.";
                if (e.getMessage() != null) {
                    msgError += e.getMessage();
                }
                req.setAttribute("msgError", msgError);
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/error.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}
