package pl.sdacademy.spring.car_dealer.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pl.sdacademy.spring.car_dealer.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcTemplateCustomerFinder implements CustomerFinder{

    private final static String SEARCH_BY_LASTNAME =
            "SELECT id, name, surname, document_no, telephone, version FROM customer where surname LIKE ?";
    private final static String SEARCH_BY_FIRSTNAME_OR_LASTNAME_ =
            "SELECT id, name, surname, document_no, telephone, version FROM customer WHERE surname LIKE ? OR name LIKE ? ";

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateCustomerFinder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> searchByLastName(String lastName) {
        return jdbcTemplate.query(SEARCH_BY_LASTNAME,new Object[]{"%%" + lastName +"%%"}, new BeanPropertyRowMapper<>(Customer.class));

    }

    @Override
    public List<Customer> searchByFirstOrLastName(String name) {
        return jdbcTemplate.query(SEARCH_BY_FIRSTNAME_OR_LASTNAME_, new Object[]{"%%" + name +"%", "%%" + name +"%%"}, new NameRowMapper());


    }

    private class NameRowMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            Customer customer = new Customer();
            customer.setName(resultSet.getString("name"));
            customer.setSurname(resultSet.getString("surname"));
            customer.setDocumentNo(resultSet.getString("document_no"));
            customer.setTelephone(resultSet.getString("telephone"));
            return customer;
        }

    }
}
