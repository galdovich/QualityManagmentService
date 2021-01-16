package com.galdovich.qaulity.util.mail;

import com.galdovich.qaulity.util.MessageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.StringJoiner;

import java.util.Properties;

/**
 * The class represents mail service for sending email notification.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class MailUtil {
    private static final Logger logger = Logger.getLogger(MailUtil.class);
    private static final MailUtil instance = new MailUtil();
    private static final String PROPERTIES_PATH = "mail.properties";
    private static final String ACTIVATION_EMAIL_SUBJECT = "local.mail.subjectActivationAccount";
    private static final String ACTIVATION_EMAIL_MESSAGE = "local.mail.activationMessage";
    private static final String CONFIRM_COMMAND_SUFFIX = "?command=confirm_account&email=";
    private static final String REGEX_DELIMITER = "\n";

    private MailUtil() {
    }

    /**
     * Gets mail util instance.
     *
     * @return the instance
     */
    public static MailUtil getInstance() {
        return instance;
    }

    /**
     * Send message.
     *
     * @param lang      the lang
     * @param userEmail the user email
     * @param link      the link
     */
    public void sendMessage(String lang, String userEmail, String link) {
        MessageManager messageManager = new MessageManager(lang);
        StringJoiner message = new StringJoiner(REGEX_DELIMITER);
        String subject;
        subject = messageManager.getMessage(ACTIVATION_EMAIL_SUBJECT);
        message.add(messageManager.getMessage(ACTIVATION_EMAIL_MESSAGE));
        message.add(link + CONFIRM_COMMAND_SUFFIX + userEmail);
        MailSender sender = new MailSender(userEmail, subject, message.toString(), initializeProperties());
        sender.sent();
    }

    private Properties initializeProperties() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Properties properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream(PROPERTIES_PATH));
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while reading mail properties", e);
        }
        return properties;
    }
}