package uz.tuit;

import uz.tuit.service.CountryCurrencyAkkaService;
import uz.tuit.service.CountryCurrencyStandardService;

import java.io.IOException;

public class Launcher {
    public static void main(String args[]) throws IOException {
//        CountryCurrencyStandardService simpleService = new CountryCurrencyStandardService();
//        simpleService.processData();

        CountryCurrencyAkkaService akkaService = new CountryCurrencyAkkaService();
        akkaService.processData();

    }
}
