package ru.mail.dimaushenko.service.model;

import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "items")
public class ItemXmlDTOList {

    @ElementList(required = true, inline = true)
    private List<ItemXmlDTO> items = new ArrayList<>();

    public List<ItemXmlDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemXmlDTO> items) {
        this.items = items;
    }

}
