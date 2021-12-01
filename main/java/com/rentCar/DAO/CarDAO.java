package com.rentCar.DAO;

import com.rentCar.entity.car.*;
import java.util.List;

public interface CarDAO {

    List<Car> getAllCars();

    boolean createCar(Car car);

    Car getCarById(long carId);

    void deleteCar(long idDeleteCar);

    void updateCar(long idUpdateCar, Car car);

    List<String> getAllUniqueBrandsCar();
}
