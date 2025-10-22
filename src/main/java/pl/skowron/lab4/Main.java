package pl.skowron.lab4;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) {
        Document cv = new Document("Jana Kowalski - CV");
        cv.setPhoto("https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Calico_tabby_cat_-_Savannah.jpg/1200px-Calico_tabby_cat_-_Savannah.jpg");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph(
                        new ParagraphWithList().setContent("Kursy")
                                .addListItem("Języka Angielskiego")
                                .addListItem("Języka Hiszpańskiego")
                                .addListItem("Szydełkowania")
                );
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Znane technologie")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                );
        System.out.println(cv.toJson());

        try {
            cv.writeHTML(new PrintStream("cv.html", "UTF-8"));
            new PrintStream("cv.json", "UTF-8").println(cv.toJson());
            System.out.println("\nPlik cv.html i cv.json został wygenerowany.");
        } catch (FileNotFoundException e) {
            System.err.println("Nie można utworzyć pliku cv.html");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Wystąpił błąd podczas zapisywania pliku");
            e.printStackTrace();
        }
    }

}
