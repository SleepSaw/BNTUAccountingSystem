package application.dao;

import application.models.Load;
import application.models.Salary;
import application.models.Teacher;
import application.util.DBManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class LoadDAO {
    public void saveLoad(Load load){
        try (Session session = DBManager.getSession()) {
            session.beginTransaction();
            session.update(load);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Create HIBERNATE EXCEPTION");
        }
    }
    public void saveLoads(List<Teacher> teachers){
        try (Session session = DBManager.getSession()) {
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
