package br.com.dynamic.util;



import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



public class HibernateUtil {
    private static SessionFactory factory;

    static {
     

            Configuration configuration = new AnnotationConfiguration();
            configuration.configure();
            factory= configuration.buildSessionFactory();
       
    }

    public static Session getSession() {
        return factory.openSession();
    }
}
