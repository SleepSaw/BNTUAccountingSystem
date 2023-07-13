package org.bntu.accounting.bntuaccountingsystem.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TeacherDAO {
    private final SessionFactory sessionFactory;

    public TeacherDAO() {
        Configuration configuration = new Configuration().addAnnotatedClass(Teacher.class);
        sessionFactory = configuration.buildSessionFactory();
    }
    public List<Teacher> findAllTeachers(){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Teacher> teachers = session.createQuery("select t from Teacher t", Teacher.class).getResultList();
            session.getTransaction().commit();
            return teachers;
        } catch (HibernateException e) {
            System.out.println("Create HIBERNATE EXCEPTION");
        }
        return null;
    }
    public void saveTeacher( Teacher teacher){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(teacher);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Create HIBERNATE EXCEPTION");
        }
    }
}
