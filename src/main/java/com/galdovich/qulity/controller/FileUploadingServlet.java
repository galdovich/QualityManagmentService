package com.galdovich.qulity.controller;

import com.galdovich.qulity.controller.command.AttributeKey;
import com.galdovich.qulity.util.PageManager;
import com.galdovich.qulity.util.UploadType;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * The class represents the upload image controller.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
@WebServlet(urlPatterns = "/upload/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(FileUploadingServlet.class);
    private static final String UPLOAD_DIR = "uploads/";
    private static final String EMPTY = "";

    /**
     * The method allows you to upload files to the server and extract the file name and put it in the session
     *
     * @param req  the request
     * @param resp the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String uploadType = req.getParameter(AttributeKey.UPLOAD_TYPE);
        String applicationDir = req.getServletContext().getRealPath(EMPTY);
        String uploadDir = applicationDir + File.separator + UPLOAD_DIR + uploadType + File.separator;
        File iconSaveDir = new File(uploadDir);
        if (!iconSaveDir.exists()) {
            iconSaveDir.mkdirs();
        }
        req.getParts().stream().filter(part -> part.getSubmittedFileName() != null).forEach(part -> {
            try {
                String fileName = part.getSubmittedFileName();
                if (!fileName.equals(EMPTY)) {
                    part.write(uploadDir + fileName);
                    session.setAttribute(UploadType.valueOf(uploadType.toUpperCase()).getName(), fileName);
                }
            } catch (IOException exc) {
                logger.log(Level.ERROR, "Uploading error", exc);
                session.setAttribute(AttributeKey.ICON_UPLOAD, EMPTY);
            }
        });

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(PageManager.UPLOAD.getPath());
        dispatcher.forward(req, resp);
    }
}