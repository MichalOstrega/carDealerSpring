package pl.sdacademy.spring.car_dealer.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pl.sdacademy.spring.car_dealer.model.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcTemplateVehicleFinder implements VehicleFinder {

    private static final String SELECT_ALL =
            "SELECT id, manufacturer, model, production_year, mileage, fuel, price, sold, version FROM Vehicle";
    private static final String SELECT_AVAILABLE =
            "SELECT id, manufacturer, model, production_year, mileage, fuel, price, sold, version FROM Vehicle WHERE sold = ?";

    private static final String COUNT_ALL = "SELECT count(id) FROM Vehicle";
    private static final String COUNT_AVAILABLE = "SELECT count(*) FROM Vehicle WHERE sold =?";

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateVehicleFinder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Vehicle> getAvailable() {
        List<Vehicle> vehicles = jdbcTemplate.query(
                SELECT_AVAILABLE,
                new Object[]{false},
                new VehicleRowMapper());
        return vehicles;
    }

    @Override
    public List<Vehicle> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new BeanPropertyRowMapper<>(Vehicle.class));
    }

    @Override
    public Long totalCount() {
        return jdbcTemplate.queryForObject(COUNT_ALL,Long.class);

    }

    @Override
    public Long totalCountForAvailable() {
        return jdbcTemplate.queryForObject(COUNT_AVAILABLE,Long.class,new Object[] {false});
    }

    private class VehicleRowMapper implements RowMapper<Vehicle> {

        @Override
        public Vehicle mapRow(ResultSet resultSet, int i) throws SQLException {
            Vehicle vehicle = new Vehicle();

            vehicle.setId(resultSet.getLong("id"));
            vehicle.setManufacturer(resultSet.getString("manufacturer"));
            vehicle.setModel(resultSet.getString("model"));
            vehicle.setProductionYear(resultSet.getLong("production_year"));
            vehicle.setMileage(resultSet.getLong("mileage"));
            vehicle.setFuel(resultSet.getString("fuel"));
            vehicle.setPrice(resultSet.getLong("price"));
            vehicle.setSold(resultSet.getBoolean("sold"));
            vehicle.setVersion(resultSet.getLong("version"));

            return vehicle;

        }
    }
}
