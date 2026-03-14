package demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import entity.Product;
import util.HibernateUtil;

public class HQLDemo {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {

            // 1️⃣ Sort by Price (Ascending)
            System.out.println("\n=== Sort by Price ASC ===");
            Query<Product> q1 = session.createQuery(
                    "FROM Product p ORDER BY p.price ASC", Product.class);
            q1.list().forEach(System.out::println);


            // 2️⃣ Sort by Price (Descending)
            System.out.println("\n=== Sort by Price DESC ===");
            Query<Product> q2 = session.createQuery(
                    "FROM Product p ORDER BY p.price DESC", Product.class);
            q2.list().forEach(System.out::println);


            // 3️⃣ Sort by Quantity (Highest First)
            System.out.println("\n=== Sort by Quantity DESC ===");
            Query<Product> q3 = session.createQuery(
                    "FROM Product p ORDER BY p.quantity DESC", Product.class);
            q3.list().forEach(System.out::println);


            // 4️⃣ Pagination (First 3 Products)
            System.out.println("\n=== First 3 Products ===");
            Query<Product> q4 = session.createQuery(
                    "FROM Product", Product.class);
            q4.setFirstResult(0);
            q4.setMaxResults(3);
            q4.list().forEach(System.out::println);


            // 5️⃣ Next 3 Products
            System.out.println("\n=== Next 3 Products ===");
            Query<Product> q5 = session.createQuery(
                    "FROM Product", Product.class);
            q5.setFirstResult(3);
            q5.setMaxResults(3);
            q5.list().forEach(System.out::println);


            // 6️⃣ Count Total Products
            System.out.println("\n=== Total Products ===");
            Query<Long> q6 = session.createQuery(
                    "SELECT COUNT(p) FROM Product p", Long.class);
            System.out.println("Total Products: " + q6.uniqueResult());


            // 7️⃣ Count Products with Quantity > 0
            System.out.println("\n=== Products In Stock ===");
            Query<Long> q7 = session.createQuery(
                    "SELECT COUNT(p) FROM Product p WHERE p.quantity > 0",
                    Long.class);
            System.out.println("Products in Stock: " + q7.uniqueResult());


            // 8️⃣ Min and Max Price
            System.out.println("\n=== Min & Max Price ===");
            Query<Object[]> q8 = session.createQuery(
                    "SELECT MIN(p.price), MAX(p.price) FROM Product p",
                    Object[].class);

            Object[] result = q8.uniqueResult();

            System.out.println("Minimum Price: " + result[0]);
            System.out.println("Maximum Price: " + result[1]);


            // 9️⃣ Group By Description
            System.out.println("\n=== Group By Category ===");

            Query<Object[]> q9 = session.createQuery(
                    "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description",
                    Object[].class);

            List<Object[]> list = q9.list();

            for(Object[] row : list)
            {
                System.out.println(row[0] + " : " + row[1]);
            }


            // 🔟 Filter Products by Price Range
            System.out.println("\n=== Price Between 20 and 100 ===");

            Query<Product> q10 = session.createQuery(
                    "FROM Product p WHERE p.price BETWEEN 20 AND 100",
                    Product.class);

            q10.list().forEach(System.out::println);


            // 1️⃣1️⃣ LIKE Query (Names starting with D)
            System.out.println("\n=== Names starting with D ===");

            Query<Product> q11 = session.createQuery(
                    "FROM Product p WHERE p.name LIKE 'D%'", Product.class);

            q11.list().forEach(System.out::println);


        } finally {

            session.close();
            factory.close();

        }
    }
}