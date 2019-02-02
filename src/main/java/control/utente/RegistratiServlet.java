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

import model.utente.Utente;
import model.utente.UtenteBL;

import java.io.IOException;

/**
 * Servlet for registering CSU.
 */
@WebServlet("/utente/registrati")
public final class RegistratiServlet extends HttpServlet {
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
                    .getRequestDispatcher("/utente/registrati.jsp");
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
        String username, password, email, name, surname, birthday;
        int gender;

        username = req.getParameter("field-username");
        password = req.getParameter("field-password");
        email = req.getParameter("field-email");
        name = req.getParameter("field-name");
        surname = req.getParameter("field-surname");
        birthday = req.getParameter("field-birthday");
        if (req.getParameter("field-gender").equalsIgnoreCase("male")) {
            gender = Utente.SESSO_MASCHILE;
        } else if (req.getParameter("field-gender")
                .equalsIgnoreCase("female")) {
            gender = Utente.SESSO_FEMMINILE;
        } else {
            gender = Utente.SESSO_ALTRO;
        }

        UtenteBL ubl = new UtenteBL();

        try {
            boolean res = ubl.effettuaRegistrazione(username,
                    password,
                    email,
                    name,
                    surname,
                    birthday,
                    gender);
            if (res) {
                req.setAttribute("successo", true);
            } else {
                throw new RuntimeException(
                        "Errore nella compilazione del form.");
            }
        } catch (Exception e) {
            if (e.getMessage() != null) {
                req.setAttribute("errore", e.getMessage());
            } else {
                req.setAttribute("errore", "Si è verificato un errore.");
            }
        }

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/utente/registrati.jsp");
        dispatcher.forward(req, resp);
    }
}
