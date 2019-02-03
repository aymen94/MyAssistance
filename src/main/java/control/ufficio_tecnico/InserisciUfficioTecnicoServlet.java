/*
  Project: MyAssistance
  Author: TeamC
  Date: 05/01/2019
*/
package control.ufficio_tecnico;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.BasicServlet;
import model.ufficio_tecnico.UfficioTecnico;
import model.ufficio_tecnico.UfficioTecnicoBL;
import model.utente.Gestore;
import model.utente.Utente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet for inserting a technical office.
 */
@WebServlet("/gestore/ufficioTecnico")
public final class InserisciUfficioTecnicoServlet extends BasicServlet {
    /**
     * doGet method.
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        if (isUtenteLoggato(req, resp, true)) {
            List<UfficioTecnico> uffici;
            try {
                uffici = new UfficioTecnicoBL().getUfficiTecnici();
            } catch (Exception e) {
                e.printStackTrace();
                uffici = new ArrayList<UfficioTecnico>();
            }
            req.setAttribute("uffici", uffici);

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/gestore/ufficioTecnico.jsp");
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
            Utente rUser;
            rUser = (Utente) req.getSession().getAttribute("utente");

            if (rUser instanceof Gestore) {
                UfficioTecnicoBL ubl = new UfficioTecnicoBL();

                String nome = req.getParameter("field-name");
                String tel = req.getParameter("field-tel");
                String email = req.getParameter("field-email");
                String ubicazione = req.getParameter("field-location");

                UfficioTecnico ufficioTecnico = new UfficioTecnico();
                ufficioTecnico.setNome(nome);
                ufficioTecnico.setTel(tel);
                ufficioTecnico.setEmail(email);
                ufficioTecnico.setUbicazione(ubicazione);

                try {
                    boolean res = ubl.insertUfficioTecnico(ufficioTecnico);
                    if (res) {
                        req.setAttribute("successo", true);
                        doGet(req, resp);
                    } else {
                        throw new RuntimeException();
                    }
                } catch (Exception e) {
                    String msgError;
                    if (e.getMessage() != null) {
                        msgError = e.getMessage();
                    } else {
                        msgError = "Si è verificato un errore.";
                    }
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
