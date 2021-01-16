package com.galdovich.qulity.controller.command.impl.pages;

import com.galdovich.qulity.controller.Router;
import com.galdovich.qulity.controller.command.ActionCommand;
import com.galdovich.qulity.util.PageManager;

import javax.servlet.http.HttpServletRequest;

public class ClientPageCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PageManager.CLIENT_MENU.getPath());
    }
}
