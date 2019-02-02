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
        gender = Integer.parseInt(req.getParameter("field-gender"));

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
                new String();
                // TODO reindirizzamento ad una pagina che comunica l'avvenuta
                // registrazione
            } else {
                throw new RuntimeException(
                        "Errore nella compilazione del form.");
            }
        } catch (Exception e) {
            String msgError = "Si e' verificato un errore.";
            req.setAttribute("msgError", msgError);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/error.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
