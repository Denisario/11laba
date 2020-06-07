package by.shestopalov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private long id;
    private String name;
    private ProductCategory productCategory;
    private long amount;
    private long price;
}
