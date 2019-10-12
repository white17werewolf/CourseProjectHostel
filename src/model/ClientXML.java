package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Запись") //высший уровень перечисляемых объектов
public class ClientXML {
    public ClientXML() {
    }
    private List<Client> list;
    public ClientXML(List<Client> list) { this.list = list; }

    @XmlElement(name = "Строка") //подуровень
    public List<Client> getList() { return list; }

    public void setList(List<Client> list) {
        this.list = list;
    }
}
