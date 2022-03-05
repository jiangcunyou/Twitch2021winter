package com.twitch.jupiter.dao;

import com.twitch.jupiter.entity.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterDao {

    @Autowired
    private SessionFactory sessionFactory;

    public boolean register(User user){
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();

        }catch (IllegalStateException ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }finally {
            if (session != null)
                session.close();
        }
        return true;

    }
}
