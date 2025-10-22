package pl.skowron.test.lab4;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import pl.skowron.lab4.Document;
import pl.skowron.lab4.ParagraphWithList;
import pl.skowron.lab4.Photo;
import pl.skowron.lab4.Section;
import pl.skowron.lab4.UnorderedList;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

public class CVGeneratorTest {

    @Test
    void testWriteHTML(){
        String imageUrl = "jan-kowalski.png";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new Photo(imageUrl).writeHTML(ps);
        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertNotNull(result);
        assertTrue(result.contains("<img"));
        assertTrue(result.contains("/>"));
        assertTrue(result.contains("src="));
        assertTrue(result.contains(imageUrl));
    }

    @Test
    void testSerialisationAndDeserialisation(){
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
        String json = cv.toJson();
        Document deserialized = Document.fromJson(json);
        assertEquals(json, deserialized.toJson());
    }

    @Test
    void testParagraphWithListHTML() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        ParagraphWithList paragraph = new ParagraphWithList()
                .setContent("Test List")
                .addListItem("Item 1")
                .addListItem("Item 2");

        paragraph.writeHTML(ps);
        String result = os.toString();

        assertTrue(result.contains("<p>Test List</p>"));
        assertTrue(result.contains("<ul>"));
        assertTrue(result.contains("<li>Item 1</li>"));
        assertTrue(result.contains("<li>Item 2</li>"));
        assertTrue(result.contains("</ul>"));
    }

    @Test
    void testSectionHTML() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        Section section = new Section("Section");
        section.addParagraph("Paragraph");

        section.writeHTML(ps);
        String result = os.toString();

        assertTrue(result.contains("<h2>Section</h2>"));
        assertTrue(result.contains("<p>Paragraph</p>"));
    }

    @Test
    void testUnorderedListHTML() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        UnorderedList list = new UnorderedList()
                .addItem("Item 1")
                .addItem("Item 2");

        list.writeHTML(ps);
        String result = os.toString();

        assertTrue(result.contains("<ul>"));
        assertTrue(result.contains("<li>Item 1</li>"));
        assertTrue(result.contains("<li>Item 2</li>"));
        assertTrue(result.contains("</ul>"));
    }
}
