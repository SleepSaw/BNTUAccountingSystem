package org.bntu.accounting.bntuaccountingsystem.dao;

import org.bntu.accounting.bntuaccountingsystem.models.Load;
import org.bntu.accounting.bntuaccountingsystem.models.Salary;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SalaryDAO {
    private final SessionFactory sessionFactory;

    public SalaryDAO() {
        Configuration configuration = new Configuration().addAnnotatedClass(Teacher.class).addAnnotatedClass(Load.class)
                .addAnnotatedClass(Salary.class);
        sessionFactory = configuration.buildSessionFactory();
    }
    public void saveSalary(Salary salary){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(salary);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println("Create HIBERNATE EXCEPTION");
        }
    }
    public void updateSalary(Salary salary){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.update(salary);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println("Create HIBERNATE EXCEPTION");
        }
    }
}
