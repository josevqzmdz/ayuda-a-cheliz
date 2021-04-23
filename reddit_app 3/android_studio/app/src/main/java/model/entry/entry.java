package model.entry;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "entry", strict = false)
public class entry implements Serializable {
    //aqui colocaremos todos los tags del tag <entry> del XML de reddit

    // GET Y SET:
    // https://stackoverflow.com/questions/23897215/how-to-automatically-generate-getters-and-setters-in-android-studio
    @Element(name = "content")
    private String content;

    @Element(required = false, name = "author")
    private author author;

    @Element(name = "title")
    private String title;

    @Element(name = "updated")
    private String updated;

    //constructor vacio
    public entry() {

    }

    public entry(String content, author author, String title, String updated) {
        this.content = content;
        this.author = author;
        this.title = title;
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "entry{" +
                "content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public author getAuthor() {
        return author;
    }

    public void setAuthor(author author) {
        this.author = author;
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
}
