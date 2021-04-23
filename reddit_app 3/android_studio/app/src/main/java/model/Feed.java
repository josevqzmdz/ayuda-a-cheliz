package model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

import model.entry.entry;

/*
    clase que crea una instancia serializable de los atributos a encontrar
    en el RSS feed de reddit
 */

// strict se asegura de que no se tomen los tags de XML de reddit
// al menos que todos los parametros del metodo feed se cumplan
// en false se pueden
@Root(name = "feed", strict = false)
public class Feed implements Serializable {

    //se toma la referencia de cada uno de los tags de XML de reddit
    //@ crea una interfaz
    @Element(name = "icon")
    private String icon;

    @Element(name = "id")
    private String id;

    @Element(name = "logo")
    private String logo;

    @Element(name = "title")
    private String title;

    @Element(name = "updated")
    private String updated;

    @Element(name = "subtitle")
    private String subtitle;

    @ElementList(inline = true, name = "entry")
    private List<entry> entrys;

    // GET y SET de cada uno de los tags de XML
    // importar clase: alt + intro
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<entry> getEntrys() {
        return entrys;
    }

    public void setEntrys(List<entry> entrys) {
        this.entrys = entrys;
    }

    @Override
    public String toString(){
        return "Feed: \n [Entrys: " +entrys +"]";

    }
}
