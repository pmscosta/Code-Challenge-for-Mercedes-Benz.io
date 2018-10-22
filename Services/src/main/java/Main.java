

public class Main {


    static String help_message =
            "command:\n" +
                    "\n\tpoll - Retrieves the status from of all configured services" +
                    "\n\tservices - Lists all known services" +
                    "\n\thistory - Outputs all the data from the local storage" +
                    "\n\thelp - This screen";

    static String bot_run = "bot command [args]\n";


    static Mercedes Benz;

    static String only = "--only";

    static String exclude = "--exclude";


    public static void printMessageHelp() {

        System.out.println(bot_run + help_message);

    }

    public static void main(String[] args) {

        if (args.length < 1 || args.length > 3 || args[0].equals("help")) {
            printMessageHelp();
            return;
        }

        Benz = new Mercedes();


        switch (args[0]) {
            case "poll":
                if (args.length > 2) {
                    printMessageHelp();
                    break;
                }

                Benz.createServices();

                if (args.length == 1) {

                    Benz.checkServices();

                } else {

                    String[] option = args[1].split("=");

                    String choices = option[1];

                    String[] services = choices.split(",");

                    if (option[0].equals(only)) {

                        Benz.checkServices(services);

                    } else if (option[0].equals(exclude)) {

                        Benz.checkAllBut(services);

                    }

                }
                break;
            case "history":
                Benz.listHistory();
                break;
            case "services":
                Benz.listServices();
                break;
            default:
                break;
        }


    }

}
