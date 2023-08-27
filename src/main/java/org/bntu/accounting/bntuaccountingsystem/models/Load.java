package org.bntu.accounting.bntuaccountingsystem.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "load")
public class Load implements Serializable {
    @Id
    @OneToOne()
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;
    @Column(name = "academic_load")
    private double academicLoad;
    @Column(name = "organization_load")
    private double orgLoad;
    @Column(name = "additional_load")
    private double addLoad;
    @Column(name = "total_load")
    private double totalLoad;

    public Load() {
    }

    public Load(double academicLoad, double orgLoad, double addLoad) {
        this.academicLoad = academicLoad;
        this.orgLoad = orgLoad;
        this.addLoad = addLoad;
        this.totalLoad = academicLoad + orgLoad + addLoad;
    }

    public double getAcademicLoad() {
        return roundValue(academicLoad);
    }

    public void setAcademicLoad(double academicLoad) {
        this.academicLoad = academicLoad;
    }

    public double getOrgLoad() {
        return roundValue(orgLoad);
    }

    public void setOrgLoad(double orgLoad) {
        this.orgLoad = orgLoad;
    }

    public double getAddLoad() {
        return roundValue(addLoad);
    }

    public void setAddLoad(double addLoad) {
        this.addLoad = addLoad;
    }

    public double getTotalLoad() {
        totalLoad = academicLoad + addLoad + orgLoad;
        return roundValue(totalLoad);
    }

    public void setTotalLoad(double totalLoad) {
        this.totalLoad = totalLoad;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        teacher.setLoad(this);
    }

    @Override
    public String toString() {
        return "Load{" +
                " academicLoad=" + academicLoad +
                ", orgLoad=" + orgLoad +
                ", addLoad=" + addLoad +
                ", totalLoad=" + totalLoad + "\n" +
                ", teacher=" + teacher +
                '}';
    }
    private double roundValue(double value){
        double result = Math.round(value * 100);
        result = result/100;
        return result;
    }
    public void clone(Load load){
        this.setTeacher(load.getTeacher());
        this.setTotalLoad(load.getTotalLoad());
        this.setAcademicLoad(load.getAcademicLoad());
        this.setOrgLoad(load.getOrgLoad());
        this.setAddLoad(load.getAddLoad());
    }
}
