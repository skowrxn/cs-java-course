package pl.skowron.lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    private List<ListItem> items = new ArrayList<>();

    public UnorderedList addItem(String content) {
        items.add(new ListItem(content));
        return this;
    }

    public void writeHTML(PrintStream out) {
        out.println("<ul>");
        for (ListItem item : items) {
            item.writeHTML(out);
        }
        out.println("</ul>");
    }
}
