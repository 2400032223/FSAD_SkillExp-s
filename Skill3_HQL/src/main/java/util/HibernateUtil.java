package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Product;

public class HibernateUtil {

private static SessionFactory factory;

public static SessionFactory getSessionFactory()
{
if(factory==null)
{
factory=new Configuration()
.configure()
.addAnnotatedClass(Product.class)
.buildSessionFactory();
}

return factory;
}

}