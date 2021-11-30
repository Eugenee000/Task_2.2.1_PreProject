package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void addCar(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listOfUserByCar(String model, int series) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User user" +
                " LEFT OUTER JOIN FETCH user.car cars" +
                " WHERE cars.model = :paramModel" +
                " AND cars.series = :paramSeries");
        query.setParameter("paramModel", model);
        query.setParameter("paramSeries", series);
        return query.getResultList();
    }
}
