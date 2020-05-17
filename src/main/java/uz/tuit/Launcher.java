package uz.tuit;

import uz.tuit.config.DatabaseManager;
import uz.tuit.service.AkkaSalesService;
import uz.tuit.service.SalesService;
import uz.tuit.service.StandardSalesService;

import java.io.IOException;

public class Launcher {
    public static void main(String args[]) throws IOException {
//        StandardSalesService simpleService = new StandardSalesService();
//        simpleService.processData();

//        SalesService salesService = new AkkaSalesService();
//        salesService.processData();

        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.connect();

    }

    public void test() {
        System.out.println(getClass().getClassLoader().getResource("D:\\1000000_Sales_Records.csv").getFile());
    }
}
