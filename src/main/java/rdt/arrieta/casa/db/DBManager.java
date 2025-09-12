package rdt.arrieta.casa.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBManager {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    /*static{
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Error al iniciar Hibernate: " + ex);
        }
    }*/

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Error al iniciar Hibernate: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
