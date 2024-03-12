package org.example;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private static Logger loger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        loger.info("Client was created with id = "+clientService.create("Ololo"));
        loger.info("List of clients  = "+clientService.listAll());
        loger.info("Client name with id 3 = "+clientService.getById(3));
        clientService.setName(3, "Blabla");
        loger.info("Set clint name Blabla with id 3 = "+clientService.getById(3));
        clientService.deleteById(9);
        loger.info("Delete client with id 3. All clients after change =  "+ clientService.listAll());
    }
}


