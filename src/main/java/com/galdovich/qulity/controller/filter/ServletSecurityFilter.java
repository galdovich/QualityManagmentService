package com.galdovich.qulity.controller.filter;

import com.galdovich.qulity.controller.ActionProvider;
import com.galdovich.qulity.controller.command.ActionCommand;
import com.galdovich.qulity.controller.command.AttributeKey;
import com.galdovich.qulity.controller.command.impl.CommandRoleAccess;
import com.galdovich.qulity.controller.command.impl.CommandType;
import com.galdovich.qulity.controller.command.impl.EmptyCommand;
import com.galdovich.qulity.entity.Role;
import com.galdovich.qulity.util.PageManager;
import com.galdovich.qulity.util.ParameterKey;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.Set;

/**
 * The class represents security filter which redirect to 403 error page
 * when user using /controller pattern with various commands.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
@WebFilter(urlPatterns = "/controller")
public class ServletSecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Role userRole = Role.valueOf((String) session.getAttribute(AttributeKey.USER_ROLE));
        String commandName = request.getParameter(ParameterKey.COMMAND);

        if (userRole == null) {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + PageManager.INDEX.getPath());
            return;
        }
        ActionCommand command = ActionProvider.getInstance().defineCommand(request);
        if (command.getClass() != EmptyCommand.class) {
            Set<CommandType> commandTypeSet = switch (userRole) {
                case WORKER -> CommandRoleAccess.WORKER.getAccessCommands();
                case ADMIN -> CommandRoleAccess.ADMIN.getAccessCommands();
                case GUEST -> CommandRoleAccess.GUEST.getAccessCommands();
            };
            if (commandTypeSet != null && !commandTypeSet.contains(CommandType.valueOf(commandName.toUpperCase()))) {
                request.getRequestDispatcher(PageManager.INDEX.getPath()).forward(request, response);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}