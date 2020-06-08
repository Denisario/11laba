package by.shestopalov.customtags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class sdaSubmit extends TagSupport {
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter writer=pageContext.getOut();
        try {
            writer.println("<input type=\"submit\" value="+value+">");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
