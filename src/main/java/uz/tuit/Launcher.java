package uz.tuit;

import java.io.IOException;

public class Launcher {
    public static void main(String args[]) throws IOException {
        CountryCurrencySimpleService simpleService = new CountryCurrencySimpleService();
        simpleService.processData();

    }
}
