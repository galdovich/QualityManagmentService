package com.galdovich.qulity.controller.command.impl;

import com.galdovich.qulity.controller.command.ActionCommand;
import com.galdovich.qulity.controller.command.impl.pages.ClientPageCommand;
import com.galdovich.qulity.controller.command.impl.pages.LoginPageCommand;
import com.galdovich.qulity.controller.command.impl.pages.RegisterPageCommand;

public enum CommandType {
    WELCOME(new WelcomeCommand()),
    LOGIN(new LoginCommand()),
    LOGIN_PAGE(new LoginPageCommand()),
    REGISTER(new RegisterCommand()),
    REGISTER_PAGE(new RegisterPageCommand()),
    PROFILE(new ProfileCommand()),
    EDIT_PROFILE(new EditProfileCommand()),
    LOGOUT(new LogoutCommand()),
    PRODUCT(new ProductCommand()),
    UPDATE_ROUTE_MAP(new UpdateProductRMCommand()),
    UPDATE_PRODUCT(new UpdateProductCommand()),
    ADD_PRODUCT(new AddProductCommand()),
    UPLOAD_PRODUCT(new ProductUploadCommand()),
    ADMIN_MENU(new AdminMenuCommand()),
    PAGINATION(new PaginationCommand()),
    CLIENT_PAGE(new ClientPageCommand()),
    CLIENT_PRODUCT(new ClientProductCommand()),
    LOCALE(new LocaleCommand());

    private ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
