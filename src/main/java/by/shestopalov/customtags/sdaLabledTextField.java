package by.shestopalov.customtags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class sdaLabledTextField extends TagSupport {
    private String name;
    private String label;
    private String value;

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) { this.value = value; }

    public void setLabel(String label) { this.label = label; }

    @Override
    public int doStartTag() throws JspException {
        JspWriter writer=pageContext.getOut();
        try {
            writer.println("<label for=\"custom\">"+label+"</label><input type=\"text\" name="+name+" value="+value+" id=\"custom\">");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
