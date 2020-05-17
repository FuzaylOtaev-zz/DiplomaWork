package uz.tuit;

import uz.tuit.dto.SalesDTO;
import uz.tuit.util.DataUtil;
import uz.tuit.util.DateUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class SalesCSVReader implements CSVReader<SalesDTO> {

    private BufferedReader reader;

    public SalesCSVReader() {
        try {
            reader = Files.newBufferedReader(new File("D:\\1000000_Sales_Records.csv").toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<SalesDTO> read(Integer chunkSize) {
        List<SalesDTO> sales = new ArrayList<>();
        try {
            String line;
            do {
                line = reader.readLine();
                if (line == null || line.isEmpty())
                    continue;

                SalesDTO dto = toDTO(line);
                sales.add(dto);

                if (sales.size() == chunkSize)
                    break;

            } while (line != null && !line.isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sales;
    }

    private SalesDTO toDTO(String line) {
        if (line == null || line.trim().isEmpty())
            return null;

        String[] attributes = line.split(",");

        SalesDTO dto = new SalesDTO();
        dto.setRegion(attributes[0]);
        dto.setCountry(attributes[1]);
        dto.setItemType(attributes[2]);
        dto.setSalesChannel(attributes[3]);
        dto.setOrderPriority(attributes[4]);
        dto.setOrderDate(DateUtil.getDate(attributes[5]));
        dto.setOrderId(DataUtil.getLong(attributes[6]));
        dto.setShipDate(DateUtil.getDate(attributes[7]));
        dto.setUnitSold(DataUtil.getLong(attributes[8]));
        dto.setUnitPrice(DataUtil.getDouble(attributes[9]));
        dto.setUnitPrice(DataUtil.getDouble(attributes[10]));
        dto.setTotalRevenue(DataUtil.getDouble(attributes[11]));
        dto.setTotalCost(DataUtil.getDouble(attributes[12]));
        dto.setTotalProfit(DataUtil.getDouble(attributes[13]));

        return dto;
    }
}
