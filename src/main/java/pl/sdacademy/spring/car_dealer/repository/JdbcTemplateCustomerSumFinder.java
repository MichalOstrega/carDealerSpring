package pl.sdacademy.spring.car_dealer.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class JdbcTemplateCustomerSumFinder implements CustomerSumFinder {


    JdbcTemplate jdbcTemplate;

    private final static String SELECT_PURCHASES_WITH_CUSTOMER = "SELECT p.price, c.document_no, c.id FROM purchase p INNER JOIN customer c ON p.customer_id=c.id";


    public JdbcTemplateCustomerSumFinder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Map<String, Long> purchasesSumByCustomer() {
        return jdbcTemplate.query(SELECT_PURCHASES_WITH_CUSTOMER, new PurchaseResultSetExtraxtor());


    }

    private class PurchaseResultSetExtraxtor implements ResultSetExtractor<Map<String, Long>> {

        @Override
        public Map<String, Long> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            LinkedHashMap<String, Long> resultDocument = new LinkedHashMap<>();
            LinkedHashMap<Long, Long> resultId = new LinkedHashMap<>();
            while (resultSet.next()) {
                String document_no = resultSet.getString("document_no");
                Long id = resultSet.getLong("id");
                Long price = resultSet.getLong("price");

                resultId.merge(id, price, (oldValue, newValue) -> oldValue + newValue);
                Long sum = resultId.get(id);

                resultDocument.merge(document_no, sum, (oldValue, newValue) -> oldValue + newValue);
            }


            return resultDocument;
        }
    }
}
