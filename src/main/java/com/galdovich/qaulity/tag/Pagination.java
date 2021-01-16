package com.galdovich.qaulity.tag;

import com.galdovich.qaulity.controller.command.AttributeKey;
import com.galdovich.qaulity.entity.Product;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The class represents the Product page pagination tag.
 *
 * Custom tag. Processes a list of products for displaying them on a page,
 * adds a pagination view for more convenient viewing of the list.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Pagination extends TagSupport {
    private static final int PAGE_CAPACITY = 5;

    @Override
    public int doStartTag() throws JspException {
        ResourceBundle bundle;
        HttpSession session = pageContext.getSession();
        String loc = (String) session.getAttribute(AttributeKey.LOCALE);

        // TODO: 08.12.2020
        bundle = loc.equals("en_us") ?
                ResourceBundle.getBundle(AttributeKey.BUNDLE_LOCALE, new Locale("en", "US")) :
                ResourceBundle.getBundle(AttributeKey.BUNDLE_LOCALE, new Locale(loc));

        List<Product> list = (List<Product>) session.getAttribute(AttributeKey.PRODUCT_LIST);
        int currentPage = (int) session.getAttribute(AttributeKey.PRODUCT_PAGE);
        int fromIndex = currentPage * PAGE_CAPACITY - PAGE_CAPACITY;
        int toIndex = Math.min(currentPage * PAGE_CAPACITY, list.size());
        if (currentPage == -1) {
            fromIndex = 0;
            toIndex = list.size();
        }
        try {
            JspWriter out = pageContext.getOut();
            for (int i = fromIndex; i < toIndex; i++) {
                out.write("<tr>");
                out.write("<td class=\"text-center\">" + list.get(i).getName() + "</td>");
                out.write("<td class=\"text-center\">" + list.get(i).getDecimal_number() + "</td>");
                out.write("<td class=\"text-center\">" + list.get(i).getOrder().getCustomer().getName() + "</td>");
                out.write("<td class=\"text-center\">" + list.get(i).getOrder().getName() + "</td>");
                out.write("<td class=\"text-center\">" + list.get(i).getPriority() + "</td>");
                out.write("<td class=\"text-center\">");
                out.write("<div class=\"progress\" style=\"height: 30px\">");
                out.write("<div class=\"progress-bar bg-success\" role=\"progressbar\"");
                out.write("style=\"width:" + list.get(i).getProgress() + "%;\"");
                out.write("aria-valuenow=\"90\" aria-valuemin=\"0\"");
                out.write("aria-valuemax=\"100\">" + list.get(i).getProgress() + "%");
                out.write("</div>");
                out.write("</div>");
                out.write("</td>");
                out.write("<td class=\"text-center\">");
                out.write("<a href=\"controller?command=product&product_id=" + list.get(i).getId() + "\"");
                out.write("class=\"btn btn-info badge-pill btn-sm\" role=\"button\"");
                out.write(" aria-pressed=\"true\">");
                out.write(bundle.getString("admin_menu.button.open") + "</a>");
                out.write("<a href=\"controller?command=product_edit&product_id=" + list.get(i).getId() + "\">");
                out.write("<img class=\"ml-1 mr-1\"");
                out.write("src=\"/jpg/icons/pencil.svg\" alt=\"\"");
                out.write("width=\"23\" height=\"23\" title=\"Bootstrap\">");
                out.write("</a>");
                out.write("<a href=\"controller?command=product_delete&product_id=" + list.get(i).getId() + "\">");
                out.write("<img src=\"/jpg/icons/trash.svg\" alt=\"\"");
                out.write("width=\"23\" height=\"23\" title=\"Bootstrap\">");
                out.write("</a>");
                out.write("</td>");
                out.write("</tr>");
            }

        } catch (IOException exc) {
            throw new JspException(exc.getMessage());
        }
        return SKIP_BODY;
    }
}
