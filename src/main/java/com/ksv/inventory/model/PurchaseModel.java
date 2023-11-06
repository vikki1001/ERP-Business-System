package com.ksv.inventory.model;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import com.ksv.inventory.HibernateUtil;
import com.ksv.inventory.dao.PurchaseDao;
import com.ksv.inventory.entity.Purchase;

public class PurchaseModel implements PurchaseDao {

    private static Session session;
    
    @Override
    public ObservableList<Purchase> getPurchases() {
        
        ObservableList<Purchase> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Purchase> products = session.createQuery("from Purchase").list();
        session.beginTransaction().commit();
        products.stream().forEach(list::add);

        return list;
    }

    @Override
    public Purchase getPurchase(long id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Purchase purchase = session.get(Purchase.class, id);
        session.getTransaction().commit();

        return purchase;
    }

    @Override
    public void savePurchase(Purchase purchase) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(purchase);
        session.getTransaction().commit();
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Purchase p = session.get(Purchase.class, purchase.getId());
        p.setProduct(purchase.getProduct());
        p.setSupplier(purchase.getSupplier());
        p.setQuantity(purchase.getQuantity());
        p.setDate(purchase.getDate());
        session.getTransaction().commit();
    }

    @Override
    public void deletePurchase(Purchase purchase) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Purchase p = session.get(Purchase.class, purchase.getId());
        session.delete(p);
        session.getTransaction().commit();
    }
    
}
