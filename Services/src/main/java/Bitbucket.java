import org.jsoup.select.Elements;

import java.io.PrintWriter;
import java.util.Objects;

public class Bitbucket extends Service {

    String goodOutput = "All Systems Operational";

    public Bitbucket(String name, String url, PrintWriter writer) {
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

        Elements div =  this.doc.select("span[class=status font-large]");
        String text = div.text();


        if(Objects.equals(text, this.goodOutput))
            result += " up";
        else
            result += " down";

        System.out.println(result);

        this.file.println(result);
    }
}
