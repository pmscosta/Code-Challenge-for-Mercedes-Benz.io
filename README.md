# Code-Challenge-for-Mercedes-Benz.io

This is my solution for the Mercedes-Benz io challenged presented @ [FEUP Carrer Fair 2018](https://web.fe.up.pt/~careerf18/).

My name is Pedro Costa and I'm currently in the 3rd grade of [MIEIC at FEUP](https://sigarra.up.pt/feup/en/cur_geral.cur_view?pv_curso_id=742). Any doubts feel free to contact me: up201605339@fe.up.pt

This bot checks if a certain service (defined in the services.txt file in the resources folder) is live or not. 
It starts by checking if the URL is actually acessible and if such it scrapes the website for more information regarding the current status. For the scraping part, I'm using an external library, [JSOUP](https://jsoup.org/). In order to keep the dependencies and the compile part simple, I integrated the project with the Maven Framework. To run the bot just simple type: `java -jar bot-1.jar` wherever you clone this repository.

The program also creates a log file stored in your home directory.

## Usage
```java
  java -jar bot1.jar command [args]
     
     command:
            poll - Retrieves the status from of all configured services
            services - Lists all known services
            history - Outputs all the data from the local storage
            help - This screen
            
     args: (only applicable to poll command)
            --only=service1, ... - Only checks the specified services
            --exclude=service1, ... - Ignores specified services
```
