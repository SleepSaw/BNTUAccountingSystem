package org.bntu.accounting.bntuaccountingsystem.dao;

import org.bntu.accounting.bntuaccountingsystem.models.Load;
import org.bntu.accounting.bntuaccountingsystem.models.Salary;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SalaryDAO {
    private final SessionFactory sessionFactory;

    public SalaryDAO() {
        Configuration configuration = new Configuration().addAnnotatedClass(Teacher.class).addAnnotatedClass(Load.class)
                .addAnnotatedClass(Salary.class);
        sessionFactory = configuration.buildSessionFactory();
    }
}
