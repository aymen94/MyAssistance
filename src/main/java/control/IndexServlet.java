/*
  Project: MyAssistance
  Author: Aymen
  Date: 05/01/2019
*/
package control;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.utente.Gestore;
import model.utente.Utente;

import java.io.IOException;

/**
 * Servlet for redirecting to various index.
 */
@WebServlet("")
public class IndexServlet extends HttpServlet {
    /**
     * Method doGet.
     */
    @Override protected void doGet(final HttpServletRequest req,
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
            if (rUser instanceof Gestore) {
                resp.sendRedirect("./gestore/");
            } else {
                resp.sendRedirect("./utente/");
            }
        }
    }
    /**
     * Method doPost.
     */
    @Override protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
