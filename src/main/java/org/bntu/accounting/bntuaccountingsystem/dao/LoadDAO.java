package org.bntu.accounting.bntuaccountingsystem.dao;

import jakarta.persistence.PersistenceException;
import org.bntu.accounting.bntuaccountingsystem.models.Load;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Objects;

public class LoadDAO {
    private final SessionFactory sessionFactory;

    public LoadDAO() {
        Configuration configuration = new Configuration().addAnnotatedClass(Teacher.class).addAnnotatedClass(Load.class);
        sessionFactory = configuration.buildSessionFactory();
    }
    public void saveLoads(List<Teacher> teachers){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            teachers.forEach(teacher -> {
                try{
                    Load load = session.get(Load.class, teacher.getId());
                    load.setAcademicLoad(teacher.getLoad().getAcademicLoad());
                    load.setOrgLoad(teacher.getLoad().getOrgLoad());
                    load.setAddLoad(teacher.getLoad().getAddLoad());

                }
                catch (NullPointerException e){
                    teacher.getLoad().setTeacher(teacher);
                    session.save(teacher.getLoad());
                }
            });
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Create HIBERNATE EXCEPTION");
        }
    }
}
