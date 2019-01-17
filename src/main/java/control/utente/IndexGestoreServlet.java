/*
  Project: MyAssistance
  Author: TeamC
  Date: 05/01/2019
*/
package control.utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for redirecting to the index of the manager.
 */
@WebServlet("/gestore/")
public class IndexGestoreServlet extends HttpServlet {
    /**
     *
     */
    @Override public void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /**
     *
     */
    @Override public void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
