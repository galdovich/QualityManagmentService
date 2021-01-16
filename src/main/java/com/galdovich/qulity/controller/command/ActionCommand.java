package com.galdovich.qulity.controller.command;

import com.galdovich.qulity.controller.Router;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    Router execute(HttpServletRequest request);
}
