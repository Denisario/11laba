package by.shestopalov.customtags;

import by.shestopalov.dbcontroller.ProductController;
import by.shestopalov.model.Product;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;

public class sdaTable extends TagSupport {


    private ArrayList<Product> products;
    private ProductController productController;

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public int doStartTag() throws JspException {

        JspWriter writer=pageContext.getOut();

        try {
            //productController=new ProductController();
            //products=productController.getAllProducts();

            writer.println("<table border=\"1\">");
            writer.println("<tr>\n" +
                    "        <th>Id</th>\n" +
                    "        <th>Product</th>\n" +
                    "        <th>Category</th>\n" +
                    "        <th>Amount</th>\n" +
                    "        <th>Price</th>\n" +
                    "        <th>Edit</th>\n" +
                    "    </tr>");

            for (Product product:products) {
                String link="<a href=\"editProduct?id="+product.getId()+"\">Edit</a>";
                writer.println("<tr>\n" +
                        "            <td>"+product.getId()+"</td>\n" +
                        "            <td>"+product.getName()+"</td>\n" +
                        "            <td>"+product.getProductCategory().getName()+"</td>\n" +
                        "            <td>"+product.getAmount()+"</td>\n" +
                        "            <td>"+product.getPrice()+"</td>\n" +
                        "            <td>"+link+"</td>\n" +
                        "        </tr>");
            }
            writer.println("</table>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
