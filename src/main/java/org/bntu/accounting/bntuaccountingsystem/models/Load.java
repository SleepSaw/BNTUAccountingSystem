package org.bntu.accounting.bntuaccountingsystem.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "load")
public class Load implements Serializable {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
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
        return academicLoad;
    }

    public void setAcademicLoad(double academicLoad) {
        this.academicLoad = academicLoad;
    }

    public double getOrgLoad() {
        return orgLoad;
    }

    public void setOrgLoad(double orgLoad) {
        this.orgLoad = orgLoad;
    }

    public double getAddLoad() {
        return addLoad;
    }

    public void setAddLoad(double addLoad) {
        this.addLoad = addLoad;
    }

    public double getTotalLoad() {
        totalLoad = academicLoad + addLoad + orgLoad;
        return totalLoad;
    }

    public void setTotalLoad(double totalLoad) {
        this.totalLoad = totalLoad;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
}
