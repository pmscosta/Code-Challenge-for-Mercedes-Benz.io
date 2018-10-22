import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Mercedes {

    String fileName;

    HashMap<String, Service> services = new HashMap<>();

    PrintWriter writer;

    String outputfileName = "history.txt";

    ClassLoader classLoader;


    private List<String> getAllServices() {

        InputStream inputStream = classLoader.getResourceAsStream("services.txt");

        List<String> doc = new BufferedReader(new InputStreamReader(inputStream,
                StandardCharsets.UTF_8)).lines().collect(Collectors.toList());

        return doc;

    }

    private void createService(String service) {

        String[] parts = service.split("\\|");

        String url = parts[1];
        String site = parts[0];

        switch (site) {
            case "github":
                this.services.put(site, new Github(site, url, writer));
                break;
            case "slack":
                this.services.put(site, new Slack(site, url, writer));
                break;
            case "bitbucket":
                this.services.put(site, new Bitbucket(site, url, writer));
                break;
            case "gitlab":
                this.services.put(site, new Gitlab(site, url, writer));
                break;
            default:
                break;
        }
    }

    public void checkServices() {

        this.createServices();

        for (Service s : this.services.values()) {
            s.runService();
            s.scrape();
        }

        this.writer.close();
    }

    public void checkServices(String[] services) {

        for (String s_name : services) {

            Service s = this.services.get(s_name);

            if (s != null) {
                s.runService();
                s.scrape();
            }


        }

        this.writer.close();

    }

    public void checkAllBut(String[] services) {

        for (Service s : this.services.values()) {

            if (Arrays.asList(services).contains(s.getName())) {
                continue;
            }

            s.runService();
            s.scrape();
        }

        this.writer.close();

    }


    public void listHistory() {


        try {

            BufferedReader br = new BufferedReader(new FileReader(this.outputfileName));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            System.out.println("Could not open History File!");
        }


    }

    public void listServices() {

        List<String> lines = this.getAllServices();

        lines.forEach(System.out::println);

    }

    private void createOutputFile() {

        String path = System.getProperty("user.home");

        path += File.separator + "Mercedes-Bot-History";

        new File(path).mkdirs();


        try {

            this.outputfileName = path + File.separator + this.outputfileName;

            FileWriter fw = new FileWriter(this.outputfileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            this.writer = new PrintWriter(bw);

        } catch (IOException e) {
            System.out.println("Cannot create a log file. History will not be available.");
        }


    }

    public Mercedes() {

        this.classLoader = Mercedes.class.getClassLoader();

        this.createOutputFile();

    }

    public void createServices() {

        List<String> services = getAllServices();

        services.forEach(this::createService);

    }


}
