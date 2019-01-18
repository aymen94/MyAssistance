/*
  Project: MyAssistance
  Author: TeamC
  Date: 05/01/2019
*/
package control.segnalazione;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.segnalazione.Segnalazione;
import model.segnalazione.SegnalazioneBL;
import model.segnalazione.SegnalazioneDB;
import model.segnalazione.Tipologia;
import model.utente.Utente;

import java.io.IOException;

/**
 * Servlet to make a reporting.
 */
@WebServlet("/utente/effettuaSegnalazione")
public class EffettuaSegnalazioneServlet extends HttpServlet {
    /**
     * Method doGet.
     */
    @Override public void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(
                        "/utente/creaSegnalazione.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * Method doPost.
     */
    @Override public void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        SegnalazioneDB sdb = new SegnalazioneDB();
        SegnalazioneBL sbl = new SegnalazioneBL(sdb);

        Tipologia tipologia = new Tipologia();
        tipologia.setId(Integer.parseInt(req.getParameter("field-type")));

        Segnalazione segnalazione  = new Segnalazione();
        segnalazione.setTitolo(req.getParameter("field-title"));
        segnalazione.setDescrizione(req.getParameter("field-descr"));
        segnalazione.setTipologia(tipologia);
        segnalazione.setAutore(
                (Utente) req.getSession().getAttribute("utente"));

        boolean res = sbl.insertSegnalazione(segnalazione);

        //TODO in caso di errore nell'inserimento della segnalazione effettuare
        //una redirect ad error.jsp con messaggio di errore
    }
}
