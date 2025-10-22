package pl.skowron.lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {

    private String title;
    private List<Paragraph> paragraphs = new ArrayList<>();

    public Section(String title) {
        this.title = title;
    }

    public Section setTitle(String title){
        this.title = title;
        return this;
    }

    public String getTitle(){
        return this.title;
    }

    public Section addParagraph(String paragraphText){
        paragraphs.add(new Paragraph(paragraphText));
        return this;
    }

    public Section addParagraph(Paragraph p){
        paragraphs.add(p);
        return this;
    }

    public void writeHTML(PrintStream out){
        out.printf("<h2>%s</h2>\n", title);
        for (Paragraph p : paragraphs) {
            p.writeHTML(out);
        }
    }

}
