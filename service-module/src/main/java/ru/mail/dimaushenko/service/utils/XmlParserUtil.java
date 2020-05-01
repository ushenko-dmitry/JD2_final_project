package ru.mail.dimaushenko.service.utils;

import java.io.File;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.mail.dimaushenko.service.model.ItemXmlDTOList;

public class XmlParserUtil {

    public static ItemXmlDTOList getItemXmlDTO(String filename) throws Exception {
        Serializer serializer = new Persister();
        File file = new File(filename);
        System.out.println(file.isFile());
        ItemXmlDTOList itemXmlDTOList = serializer.read(ItemXmlDTOList.class, file);
        return itemXmlDTOList;
    }

}
