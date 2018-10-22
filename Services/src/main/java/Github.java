import org.jsoup.nodes.Element;

import java.io.PrintWriter;
import java.util.Objects;

public class Github extends Service {

    String goodOutput = "good";

    public Github(String name, String url, PrintWriter writer) {
        super(name, url, writer);
    }

    @Override
    public void scrape() {
        String result = this.getNameInfo();

        if(!canBeScraped){
            result += "down";
            this.file.println(result);
            return;
        }

        Element div =  this.doc.getElementById("message-list");
        String attr = div.attr("data-last-known-status");

        if(Objects.equals(attr, this.goodOutput))
            result += " up";
        else
            result += " down";

        System.out.println(result);

        this.file.println(result);
    }
}
