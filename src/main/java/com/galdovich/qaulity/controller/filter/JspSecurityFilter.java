package com.galdovich.qaulity.controller.filter;

import com.galdovich.qaulity.util.PageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The filter's task is to redirect direct calls to jsp pages located in the package
 * 'WEB-INF/jsp' of webapp to the index page.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
@WebFilter(urlPatterns = "/jsp/*")
public class JspSecurityFilter implements Filter {
    private static final Logger logger = Logger.getLogger(JspSecurityFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        logger.log(Level.INFO, "Invalid action from " + request.getRequestURL());
        response.sendRedirect(request.getContextPath() + PageManager.INDEX.getPath());
    }
}
