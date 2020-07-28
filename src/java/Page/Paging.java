/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author cauch
 */
public class Paging extends SimpleTagSupport{

    public static String hyperlink(String text, String url) {
        return "<a class=\"btn btn-black\" href=\"" + url + "\" >" + text + "</a>";
    }

    public static String label(String text) {
        return "<span " + "class=" + "\"btn btn-primary\"" + "style=\"border: solid ##00ff1e;color: #ffffff\"" + ">" + text + "</span>";
    }

    public static String pager(int gap, int pageindex, int totalpage) {

        String result = "";
        if (pageindex - gap > 1) {
            result += hyperlink("", "?page=1");
        }

        for (int i = gap; i > 0; i--) {
            int page = pageindex - i;
            if (page > 0) {
                result += hyperlink("" + page, "?page=" + page);
            }
        }

        result += label("" + pageindex);

        for (int i = 1; i <= gap; i++) {
            int page = pageindex + i;
            if (page <= totalpage) {
                result += hyperlink("" + page, "?page=" + page);
            }
        }

        if (pageindex + gap < totalpage) {
            result += hyperlink("", "?page=" + totalpage);
        }

        return result;
    }

}
