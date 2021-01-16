package com.galdovich.qaulity.controller;

import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.controller.command.AttributeKey;
import com.galdovich.qaulity.dao.pool.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The main controller in project.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * Process request.
     * Receives a request from the client, retrieves the name of the requested command,
     * searches for this command from the list of existing ones, and redirects the
     * request to the command for processing. Based on the processing results, it
     * generates a response and redirects or forwards to the required page.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ActionProvider provider = ActionProvider.getInstance();
        ActionCommand command = provider.defineCommand(request);
        Router router = command.execute(request);
        String page = router.getPage();
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, page);

        if (router.getTransition() == Router.Transition.FORWARD) {
            request.getRequestDispatcher(page).forward(request, response);
        } else {
            response.sendRedirect(page);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}
