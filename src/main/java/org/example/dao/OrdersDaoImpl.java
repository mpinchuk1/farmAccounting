package org.example.dao;

import org.example.entity.Order;
import org.example.utils.EmUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao {
    private final EmUtil util = new EmUtil();
    private final EntityManager em = util.getEm();

    public OrdersDaoImpl() {
    }

    @Override
    public List<Order> selectAllOrders() {
        TypedQuery<Order> query = em.createQuery("select o from Order o", Order.class);

        return query.getResultList();
    }

    @Override
    public boolean addOrder(Order order) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try{
            em.persist(order);
            transaction.commit();
        }catch (Exception ex){
            if(transaction.isActive())
                transaction.rollback();
            ex.printStackTrace();
            return false;
        }
        System.out.println(order);
        System.out.println("Order " + order.getBird() + " has added to DB!");
        return true;
    }

    @Override
    public void deleteOrder(Long id) {
        EntityTransaction transaction = em.getTransaction();

        Order delOrder = new Order();

        transaction.begin();
        try{
            delOrder =  em.find(Order.class, id);
            em.remove(delOrder);
            transaction.commit();
        }catch (Exception ex){
            if(transaction.isActive())
                transaction.rollback();
            ex.printStackTrace();
            return;
        }
        System.out.println("Order " + delOrder.getBird() + " has deleted from DB!");
    }

    public EntityManager getEm(){
        return em;
    }
}
