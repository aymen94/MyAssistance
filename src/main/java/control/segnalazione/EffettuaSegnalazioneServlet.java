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

import model.segnalazione.SegnalazioneBL;
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

        SegnalazioneBL sbl = new SegnalazioneBL();
        boolean res;
        Tipologia tipologia = new Tipologia();
        tipologia.setId(Integer.parseInt(req.getParameter("field-type")));
        res = sbl.insertSegnalazione(
                req.getParameter("field-title"),
                req.getParameter("field-descr"),
                tipologia, (Utente) req.getSession().getAttribute("utente"));

        //TODO in caso di errore nell'inserimento della segnalazione effettuare
        //una redirect ad error.jsp con messaggio di errore
    }
}
