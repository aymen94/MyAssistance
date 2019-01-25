/*
  Project: MyAssistance
  Author: TeamC
  Date: 05/01/2019
*/
package control.ufficio_tecnico;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ufficio_tecnico.UfficioTecnico;
import model.ufficio_tecnico.UfficioTecnicoBL;
import model.utente.Utente;
import java.io.IOException;

/**
 * Servlet for inserting a technical office.
 */
public class InserisciUfficioTecnicoServlet extends HttpServlet {
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
                ubl.insertUfficioTecnico(nome, tel, email, ubicazione);
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

    /**
     * doPost method.
     */
    @Override protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
