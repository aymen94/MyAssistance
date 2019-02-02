/*
  Project: MyAssistance
  Author: TeamC
  Date: 05/01/2019
*/
package control.utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.utente.Gestore;
import model.utente.UtenteBL;

import java.io.IOException;

/**
 * Servlet for accessing manager.
 */
@WebServlet("/gestore/accedi")
public final class AccediGestoreServlet extends HttpServlet {
    /**
     * doGet method.
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
                throws ServletException, IOException {
        if (req.getSession().getAttribute("utente") != null) {
            resp.sendRedirect("../");
        } else {
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/gestore/accedi.jsp");
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
        String username = (String) req.getParameter("username");
        String password = (String) req.getParameter("password");
        if (username != null && password != null) {
            UtenteBL u = new UtenteBL();
            try {
                Gestore csu = u.autenticazioneGestore(username, password);
                if (csu != null) {
                    req.getSession(true).setAttribute("utente", csu);
                } else {
                    throw new RuntimeException(
                            "Combinazione username / password errata.");
                }
            } catch (Exception e) {
                if (e.getMessage() != null) {
                    req.setAttribute("errore", e.getMessage());
                } else {
                    req.setAttribute("errore", "Utente non esistente.");
                }
            }
        }

        doGet(req, resp);
    }
}
