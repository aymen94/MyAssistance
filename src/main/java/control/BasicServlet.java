/*
  Project: MyAssistance
  Author: TeamC
  Date: 05/01/2019
*/
package control;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.segnalazione.Segnalazione;
import model.utente.Gestore;
import model.utente.Utente;

import java.io.IOException;

/**
 * Basic servlet to manage login.
 */
public abstract class BasicServlet extends HttpServlet {

    /**
     * Checks wheter user is logged as CSU.
     *
     * @param req  the HTTP request object from actual servlet.
     *
     * @param resp the HTTP response object from actual servlet.
     *
     * @return true is user is logged, false if it's not.
     *
     * @throws IOException from sendRedirect
     */
    protected final boolean isUtenteLoggato(final HttpServletRequest req,
            final HttpServletResponse resp) throws IOException {
        return isUtenteLoggato(req, resp, false);
    }

    /**
     * Checks wheter user is present into session.
     *
     * @param req       the HTTP request object from actual servlet.
     *
     * @param resp      the HTTP response object from actual servlet.
     *
     * @param asGestore check if user has logged as Gestore.
     *
     * @return true is user is logged, false if it's not.
     *
     * @throws IOException from sendRedirect
     */
    protected final boolean isUtenteLoggato(final HttpServletRequest req,
            final HttpServletResponse resp, final boolean asGestore)
            throws IOException {

        Utente rUser;

        rUser = (Utente) req.getSession().getAttribute("utente");

        if (rUser == null || (asGestore && !(rUser instanceof Gestore))) {
            req.getSession().invalidate();
            resp.sendRedirect(getServletContext().getContextPath() + "/");
            return false;
        }

        req.setAttribute("STATO_APERTO", Segnalazione.STATO_APERTO);
        req.setAttribute("STATO_ASSEGNATO", Segnalazione.STATO_ASSEGNATO);
        req.setAttribute("STATO_RIFIUTATO", Segnalazione.STATO_RIFIUTATO);
        req.setAttribute("STATO_RISOLTO", Segnalazione.STATO_RISOLTO);
        req.setAttribute("SESSO_ALTRO", Utente.SESSO_ALTRO);
        req.setAttribute("SESSO_MASCHILE", Utente.SESSO_MASCHILE);
        req.setAttribute("SESSO_FEMMINILE", Utente.SESSO_FEMMINILE);

        return true;
    }
}
