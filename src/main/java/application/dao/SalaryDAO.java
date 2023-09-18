package application.dao;

import application.models.Load;
import application.models.Salary;
import application.models.Teacher;
import application.util.DBManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SalaryDAO {
    public void saveSalary(Salary salary){
        try (Session session = DBManager.getSession()) {
            session.beginTransaction();
            session.save(salary);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println("Create HIBERNATE EXCEPTION");
        }
    }
    public void updateSalary(Salary salary){
        try (Session session = DBManager.getSession()) {
            session.beginTransaction();
            session.update(salary);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println("Create HIBERNATE EXCEPTION");
        }
    }
}
