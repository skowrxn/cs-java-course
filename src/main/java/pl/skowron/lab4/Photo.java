package pl.skowron.lab4;

import java.io.PrintStream;

public class Photo {

    private String url;

    public Photo(String url){
        this.url =url;
    }

    public void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"42\" width=\"42\"/>\n",url);
    }
}