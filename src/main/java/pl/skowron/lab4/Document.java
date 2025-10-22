package pl.skowron.lab4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {

    private Photo photo;
    private String title;
    private List<Section> sections = new ArrayList<>();

    public Document(String title) {
        this.title = title;
    }

    public Document setTitle(String title){
        this.title = title;
        return this;
    }

    public void setPhoto(String photoUrl){
        this.photo = new Photo(photoUrl);
    }

    public Section addSection(String sectionTitle){
        Section section = new Section(sectionTitle);
        sections.add(section);
        return section;
    }

    public void writeHTML(PrintStream out){
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.printf("<title>%s</title>\n", title);
        out.println("</head>");
        out.println("<body>");
        out.printf("<h1>%s</h1>\n", title);
        if (photo != null) {
            photo.writeHTML(out);
        }
        for (Section section : sections) {
            section.writeHTML(out);
        }
        out.println("</body>");
        out.println("</html>");
    }

    public String toJson() {
        RuntimeTypeAdapterFactory<Paragraph> adapter =
                RuntimeTypeAdapterFactory
                        .of(Paragraph.class)
                        .registerSubtype(Paragraph.class)
                        .registerSubtype(ParagraphWithList.class);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapter).setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public static Document fromJson(String json) {
        RuntimeTypeAdapterFactory<Paragraph> adapter =
                RuntimeTypeAdapterFactory
                        .of(Paragraph.class)
                        .registerSubtype(Paragraph.class)
                        .registerSubtype(ParagraphWithList.class);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapter).create();
        return gson.fromJson(json, Document.class);
    }

}
