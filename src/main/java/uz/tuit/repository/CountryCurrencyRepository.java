package uz.tuit.repository;

import uz.tuit.config.DatabaseManager;
import uz.tuit.dto.CountryCurrencyDTO;

import java.sql.SQLException;
import java.util.List;

public class CountryCurrencyRepository {

    private DatabaseManager databaseManager = new DatabaseManager();

    public void create(List<CountryCurrencyDTO> countryCurrencyList) {
        if (countryCurrencyList == null || countryCurrencyList.isEmpty())
            return;

        String sql = " INSERT INTO country_currency (country, country_code, currency, currency_code) VALUES ";

        for (int i = 0; i < countryCurrencyList.size(); i++) {
            CountryCurrencyDTO dto = countryCurrencyList.get(i);
            if (dto == null)
                continue;

            sql += "(" +
                    "'" + dto.getCountry() + "'," +
                    "'" + dto.getCountryCode() + "'," +
                    "'" + dto.getCurrency() + "'," +
                    "'" + dto.getCurrencyCode() + "'" +
                    ")" + (i == countryCurrencyList.size() - 1 ? ";" : ",");
        }

        try {
            databaseManager.executeCommand(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
