import org.jsoup.select.Elements;

import java.io.PrintWriter;
import java.util.Objects;

public class Slack extends Service {

    String goodOutput = "Smooth sailing!";

    public Slack(String name, String url, PrintWriter writer) {
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


        Elements div =  this.doc.select("h1[class=black_licorice text_center width_100");
        String text = div.text();

        if(Objects.equals(text, this.goodOutput))
            result += " up";
        else
            result += " down";

        System.out.println(result);

        this.file.println(result);
    }
}
