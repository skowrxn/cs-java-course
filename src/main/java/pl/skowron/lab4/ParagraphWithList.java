package pl.skowron.lab4;

import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {

    private UnorderedList list = new UnorderedList();

    public ParagraphWithList() {
        super();
    }

    public ParagraphWithList(String content) {
        super(content);
    }

    @Override
    public ParagraphWithList setContent(String content){
        super.setContent(content);
        return this;
    }

    public ParagraphWithList addListItem(String item){
        list.addItem(item);
        return this;
    }

    @Override
    public void writeHTML(PrintStream out){
        out.printf("<p>%s</p>\n", getContent());
        list.writeHTML(out);
    }

}
