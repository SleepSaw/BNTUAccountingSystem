package org.bntu.accounting.bntuaccountingsystem.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bntu.accounting.bntuaccountingsystem.models.Load;
import org.bntu.accounting.bntuaccountingsystem.models.Salary;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Objects;

public class TeacherDAO {
    private final SessionFactory sessionFactory;
    private final LoadDAO loadDAO;
    private final SalaryDAO salaryDAO;

    public TeacherDAO() {
        Configuration configuration = new Configuration().addAnnotatedClass(Teacher.class).addAnnotatedClass(Load.class)
                .addAnnotatedClass(Salary.class);
        sessionFactory = configuration.buildSessionFactory();
        loadDAO =  new LoadDAO();
        salaryDAO = new SalaryDAO();
    }
    public List<Teacher> findAllTeachers(){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Teacher> teachers = session.createQuery("select t from Teacher t order by t.name", Teacher.class).getResultList();
            session.getTransaction().commit();
            return teachers;
        } catch (HibernateException e) {
            System.out.println("find all exception");
        }
        return null;
    }
    public void saveTeacher(Teacher teacher){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Load load = new Load(0,0,0);
            load.setTeacher(teacher);
            Salary salary = new Salary();
            salary.setTeacher(teacher);
            session.save(teacher);
            session.save(load);
            session.save(salary);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Create HIBERNATE EXCEPTION");
        }
    }
    public void removeTeacher(Teacher teacher){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.remove(teacher);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Create HIBERNATE EXCEPTION");
        }
    }
    public void updateTeacher(Teacher updatedTeacher){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Teacher teacher = session.get(Teacher.class,updatedTeacher.getId());
            teacher.setName(updatedTeacher.getName());
            teacher.setSubject(updatedTeacher.getSubject());
            teacher.setPost(updatedTeacher.getPost());
            teacher.setQualification(updatedTeacher.getQualification());
            teacher.setYoungSpecialist(updatedTeacher.getYoungSpecialist());
            teacher.setExp(updatedTeacher.getExp());
            teacher.setCategory(updatedTeacher.getCategory());
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Create HIBERNATE EXCEPTION");
        }
    }

}
