package ru.mail.dimaushenko.service.model;

import java.math.BigDecimal;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public class ItemXmlDTO {

    @Element(name = "name", required = true)
    private String name;
    @Element(name = "description", required = true)
    private String description;
    @Element(name = "price", required = true)
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ItemXmlDTO{" + "name=" + name + ", description=" + description + ", price=" + price + '}';
    }

}
