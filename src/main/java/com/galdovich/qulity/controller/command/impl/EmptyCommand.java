package com.galdovich.qulity.controller.command.impl;

import com.galdovich.qulity.controller.Router;
import com.galdovich.qulity.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    private static final String ERROR_MESSAGE = "Incorrect command";

    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}
