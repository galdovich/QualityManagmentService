package com.galdovich.qaulity.controller.command;

import com.galdovich.qaulity.controller.command.impl.*;
import com.galdovich.qaulity.controller.command.impl.pages.ClientPageCommand;
import com.galdovich.qaulity.controller.command.impl.pages.LoginPageCommand;
import com.galdovich.qaulity.controller.command.impl.pages.RegisterPageCommand;
import com.galdovich.qaulity.controller.command.impl.*;

/**
 * The enum which define all commands in project.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
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
    ADD_PRODUCT(new AddProductCommand()),
    UPLOAD_PRODUCT(new ProductUploadCommand()),
    ADMIN_MENU(new AdminMenuCommand()),
    PAGINATION(new PaginationCommand()),
    CLIENT_PAGE(new ClientPageCommand()),
    CLIENT_PRODUCT(new ClientProductCommand()),
    LOCALE(new LocaleCommand()),

    EDIT_PRODUCT(new EditProductCommand());

    private ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public ActionCommand getCommand() {
        return command;
    }
}
