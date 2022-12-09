package jpa_on_javase;

import java.sql.SQLException;
import jpa_on_javase.entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * JavaSEからJPAを使うサンプル
 */
public class MainClass {
    
    public static void main(String[] args) throws SQLException {
        //persistence.xmlで定義されたPersistenceUnitからEntityManagerFactoryを取得
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_on_javase_pu");
        EntityManager em = emf.createEntityManager();

        //Customerの追加
        Customer customer = new Customer();
        customer.setCustomerName("customer-" + System.currentTimeMillis());

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(customer);
        tx.commit();

        //Customerの検索
        for (Customer c : em.createNamedQuery("Customer.findAll", Customer.class).getResultList()){
            System.out.println(c);
        }

        em.close();
        emf.close();

    }
}
