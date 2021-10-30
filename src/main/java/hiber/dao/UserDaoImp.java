package hiber.dao;


import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user, Car car) {
        sessionFactory.getCurrentSession().save(user);
        sessionFactory.getCurrentSession().save(car);

    }

    @Override
    public List<Car> getUser(Car car) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Car where model = :modelCar and series =:seriesCar");
        query.setParameter("modelCar", car.getModel());
        query.setParameter("seriesCar", car.getSeries());
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

}
