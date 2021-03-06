package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void addCar(Car car);

    List<User> listUsers();

    public List<User> listOfUserByCar(String model, int series);
}
