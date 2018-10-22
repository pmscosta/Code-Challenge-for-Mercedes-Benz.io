import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class Service {

    protected String name;
    protected String url;
    protected Document doc;
    protected PrintWriter file;
    protected boolean canBeScraped = true;


    public String getName() {
        return name;
    }

    /**
     * Pings a HTTP URL. This effectively sends a HEAD request and returns
     * <code>true</code> if the response code is in the 200-399 range.
     * @param url The HTTP URL to be pinged.
     * @param timeout The timeout in millis for both the connection timeout and
     * the response read timeout. Note that the total timeout is effectively two
     * times the given timeout.
     * @return <code>true</code> if the given HTTP URL has returned response code
     * 200-399 on a HEAD request within the given timeout, otherwise
     * <code>false</code>.
     */
    private boolean pingURL(String url, int timeout) {
        url = url.replaceFirst("^https",
                "http"); // Otherwise an exception may be thrown on
        // invalid SSL certificates.

        try {
            HttpURLConnection connection =
                    (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (200 <= responseCode && responseCode <= 399);
        } catch (IOException exception) {
            return false;
        }
    }

    public String getNameInfo(){
        return "[" + this.name + "] \t" + this.getTimeStamp() + " - ";
    }

    public Service(String name, String url, PrintWriter printWriter){

        this.name = name;
        this.url = url;
        this.file = printWriter;

    }

    public void runService(){

        if(!pingURL(url, 500)) {
            System.out.println(this.name + ": Network unreachable or timeout achieved. Try again later!");
            canBeScraped = false;
        }
        else {
            try {
                this.doc = Jsoup.connect(this.url).get();
            } catch (IOException e) {
                System.out.println("Invalid HTML, cannot scrape");
            }
        }

    }

    public String getTimeStamp(){
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new java.util.Date());

        return timestamp;
    }

    public abstract void scrape();



}
