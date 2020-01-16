package uz.tuit;

import uz.tuit.dto.CountryCurrencyDTO;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CountryCurrencyCSVReader implements CSVReader<CountryCurrencyDTO> {

    private BufferedReader reader;

    public CountryCurrencyCSVReader() {
        try {
            reader = Files.newBufferedReader(new File((Objects.requireNonNull(getClass().getClassLoader().getResource("country-code-to-currency-code-mapping.csv"))).getFile()).toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<CountryCurrencyDTO> read(Integer chunkSize) {
        List<CountryCurrencyDTO> countryCurrencyList = new ArrayList<>();
        try {
            String line;
            do {
                line = reader.readLine();
                if (line == null || line.isEmpty())
                    continue;

                CountryCurrencyDTO dto = toDTO(line);
                countryCurrencyList.add(dto);

                if (countryCurrencyList.size() == chunkSize)
                    break;

            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return countryCurrencyList;
    }

    private CountryCurrencyDTO toDTO(String line) {
        String[] attributes = line.split(",");

        CountryCurrencyDTO dto = new CountryCurrencyDTO();
        dto.setCountry(attributes[0]);
        dto.setCountryCode(attributes[1]);
        dto.setCurrency(attributes[2]);
        dto.setCurrencyCode(attributes[3]);

        return dto;
    }
}
