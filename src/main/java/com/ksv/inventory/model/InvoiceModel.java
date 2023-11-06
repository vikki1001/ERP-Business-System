package com.ksv.inventory.model;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import com.ksv.inventory.HibernateUtil;
import com.ksv.inventory.dao.InvoiceDao;
import com.ksv.inventory.entity.Invoice;

public class InvoiceModel implements InvoiceDao {

    private static Session session;

    @Override
    public ObservableList<Invoice> getInvoices() {

        ObservableList<Invoice> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Invoice> products = session.createQuery("from Invoice").list();
        session.beginTransaction().commit();
        products.stream().forEach(list::add);
        return list;
    }

    @Override
    public Invoice getInvoice(String id) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Invoice invoice = session.get(Invoice.class, id);
        session.getTransaction().commit();
        return invoice;
    }

    @Override
    public void saveInvoice(Invoice invoice) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(invoice);
        session.getTransaction().commit();
    }

    @Override
    public void deleteCategory(Invoice invoice) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Invoice i = session.get(Invoice.class, invoice.getId());
        session.delete(i);
        session.getTransaction().commit();
    }

}
