package application.dao;

import application.models.Load;
import application.models.Salary;
import application.models.Teacher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class LoadDAO {
    private final SessionFactory sessionFactory;

    public LoadDAO() {
        Configuration configuration = new Configuration().addAnnotatedClass(Teacher.class).addAnnotatedClass(Load.class)
                .addAnnotatedClass(Salary.class);
        sessionFactory = configuration.buildSessionFactory();
    }
    public void saveLoad(Load load){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.update(load);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Create HIBERNATE EXCEPTION");
        }
    }
    public void saveLoads(List<Teacher> teachers){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
                try{
                    for (Teacher teacher: teachers) {
                        Load load = session.get(Load.class, teacher.getId());
                        load.setAcademicLoad(teacher.getLoad().getAcademicLoad());
                        load.setOrgLoad(teacher.getLoad().getOrgLoad());
                        load.setAddLoad(teacher.getLoad().getAddLoad());
                    }
                }
                catch (NullPointerException e){
                    System.out.println("kpdapokdpoaspodkpapsd");
                }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Create HIBERNATE EXCEPTION");
        }
    }
}
