package com.rentCar.DAO;

import com.rentCar.entity.car.*;
import com.rentCar.utill.DBManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    private static final Logger logger = Logger.getLogger(CarDAO.class);


    public List<Car> getAllCars(){
        List<Car> cars = new ArrayList<>();
        try(Connection manager = DBManager.getInstance().getConnection();
            Statement statement = manager.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT car_id, car_type, brand, " +
                    "model, body_type, color, year_creation, transmission_type, " +
                    "engine_size, engine_power, fuel_type, price, is_active FROM car")) {

            while (resultSet.next()) {
                Car car = new Car();
                car.setCarId(resultSet.getLong("car_id"));
                car.setCarType(CarType.findByKey(resultSet.getInt("car_type")).toString());
                car.setBrand(resultSet.getString("brand"));
                car.setModel(resultSet.getString("model"));
                car.setBodyType(BodyType.findByKey(resultSet.getInt("body_type")).toString());
                car.setColor(Color.findByKey(resultSet.getInt("color")).toString());
                car.setYearCreation(resultSet.getInt("year_creation"));
                car.setTransmissionType(TransmissionType.findByKey(resultSet.getInt("transmission_type")).toString());
                car.setEngineSize(resultSet.getDouble("engine_size"));
                car.setEnginePower(resultSet.getInt("engine_power"));
                car.setFuelType(FuelType.findByKey(resultSet.getInt("fuel_type")).toString());
                car.setPrice(resultSet.getDouble("price"));
                car.setIsActive(resultSet.getBoolean("is_active"));
                cars.add(car);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return cars;
    }

    public boolean createCar(Car car) {
        ResultSet resultSet = null;
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO car VALUES (DEFAULT ,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, CarType.valueOf(car.getCarType()).getCarTypeId());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setInt(4, BodyType.valueOf(car.getBodyType()).getBodyTypeId());
            preparedStatement.setInt(5, Color.valueOf(car.getColor()).getColorId());
            preparedStatement.setInt(6, car.getYearCreation());
            preparedStatement.setInt(7, TransmissionType.valueOf(car.getTransmissionType()).getTransmissionId());
            preparedStatement.setDouble(8, car.getEngineSize());
            preparedStatement.setInt(9, car.getEnginePower());
            preparedStatement.setInt(10, FuelType.valueOf(car.getFuelType()).getFuelTypeId());
            preparedStatement.setDouble(11, car.getPrice());
            preparedStatement.setBoolean(12, car.getIsActive());
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                long idField = resultSet.getLong(1);
                car.setCarId(idField);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return true;
    }

    public Car getCarById(long carId) {
        Car car = new Car();
        ResultSet resultSet;
        try(Connection manager = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = manager.prepareStatement("SELECT * FROM car WHERE car_id = ?")) {
            preparedStatement.setLong(1, carId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                car.setCarId(resultSet.getLong("car_id"));
                car.setCarType(CarType.findByKey(resultSet.getInt("car_type")).toString());
                car.setBrand(resultSet.getString("brand"));
                car.setModel(resultSet.getString("model"));
                car.setBodyType(BodyType.findByKey(resultSet.getInt("body_type")).toString());
                car.setColor(Color.findByKey(resultSet.getInt("color")).toString());
                car.setYearCreation(resultSet.getInt("year_creation"));
                car.setTransmissionType(TransmissionType.findByKey(resultSet.getInt("transmission_type")).toString());
                car.setEngineSize(resultSet.getDouble("engine_size"));
                car.setEnginePower(resultSet.getInt("engine_power"));
                car.setFuelType(FuelType.findByKey(resultSet.getInt("fuel_type")).toString());
                car.setPrice(resultSet.getDouble("price"));
                car.setIsActive(resultSet.getBoolean("is_active"));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return car;
    }

    public void deleteCar(long idDeleteCar) {
        ResultSet resultSet = null;
        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM car WHERE car_id =?")){
            preparedStatement.setLong(1, idDeleteCar);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void updateCar(long idUpdateCar, Car car) {

        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE car SET car_type = ?, brand = ?, model = ?, body_type = ?, color = ?, year_creation = ?, transmission_type = ?, engine_size = ?, engine_power = ?, fuel_type = ?, price = ?, is_active = ? WHERE car_id = ?")) {

            preparedStatement.setInt(1, CarType.valueOf(car.getCarType()).getCarTypeId());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setInt(4, BodyType.valueOf(car.getBodyType()).getBodyTypeId());
            preparedStatement.setInt(5, Color.valueOf(car.getColor()).getColorId());
            preparedStatement.setInt(6, car.getYearCreation());
            preparedStatement.setInt(7, TransmissionType.valueOf(car.getTransmissionType()).getTransmissionId());
            preparedStatement.setDouble(8, car.getEngineSize());
            preparedStatement.setInt(9, car.getEnginePower());
            preparedStatement.setInt(10, FuelType.valueOf(car.getFuelType()).getFuelTypeId());
            preparedStatement.setDouble(11, car.getPrice());
            preparedStatement.setBoolean(12, car.getIsActive());
            preparedStatement.setLong(13, idUpdateCar);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
    }


    public List<String> getAllUniqueBrandsCar() {
        List<String> marks = new ArrayList<>();
        try (Connection connection = DBManager.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT brand FROM car")) {
            while (resultSet.next()) {
                marks.add(resultSet.getString("brand"));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return marks;
    }
}
