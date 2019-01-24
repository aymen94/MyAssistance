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
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(
                            "/utente/creaSegnalazione.jsp");
            dispatcher.forward(req, resp);
        }
    }

    /**
     * doPost method.
     */
    @Override protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        Utente rUser;

        rUser = (Utente) req.getSession().getAttribute("utente");

        if (rUser == null) {
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(
                            "/index.jsp");
            dispatcher.forward(req, resp);
        } else {
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

            try {
                boolean res = sbl.insertSegnalazione(segnalazione);
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
