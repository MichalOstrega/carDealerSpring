package pl.sdacademy.spring.car_dealer.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcTemplateAccountancyFinder implements AccountancyFinder {

    private static final String SELECT_SALES_WITH_DATE = "SELECT date, price FROM purchase";



    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateAccountancyFinder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Map<LocalDate, Long> purchasesValueByDay() {

        Map<LocalDate, Long> sales = jdbcTemplate.query(SELECT_SALES_WITH_DATE, new AccountancyResultSetExtractor());

        return sales;
    }

    private class AccountancyResultSetExtractor implements ResultSetExtractor<Map<LocalDate,Long>>{

        @Override
        public Map<LocalDate, Long> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Map<LocalDate, Long> purchaseByDate = new HashMap<>();
            while (resultSet.next()) {
                Date date = resultSet.getDate("date");
                LocalDate dayOfSale = date.toLocalDate();
                long price = resultSet.getLong("price");


                purchaseByDate.merge(dayOfSale, price, (oldValue, newValue) -> oldValue + newValue);
                /*
                Long sumOfSalesForDay = purchaseByDate.getOrDefault(date, 0L);
                sumOfSalesForDay += price;
                purchaseByDate.put(dayOfSale, sumOfSalesForDay);
                */


            }

            return purchaseByDate;
        }
    }
}
