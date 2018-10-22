import org.jsoup.nodes.Element;
import java.io.PrintWriter;
import java.util.Objects;

public class Gitlab extends Service {


    String goodOutput = "All Systems Operational";

    public Gitlab(String name, String url, PrintWriter printWriter) {
        super(name, url, printWriter);
    }

    @Override
    public void scrape() {

        String result = this.getNameInfo();

        if(!canBeScraped){
            result += "down";
            this.file.println(result);
            return;
        }


        Element div =  this.doc.getElementById("statusbar_text");
        String text = div.text();
        if(Objects.equals(text, this.goodOutput))
            result += " up";
        else
            result += " down";

        System.out.println(result);

        this.file.println(result);

    }
}
