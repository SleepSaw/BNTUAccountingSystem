package org.bntu.accounting.bntuaccountingsystem.models;

public class Load {
    private double academicLoad;
    private double orgLoad;
    private double addLoad;

    public Load(double academicLoad, double orgLoad, double addLoad) {
        this.academicLoad = academicLoad;
        this.orgLoad = orgLoad;
        this.addLoad = addLoad;
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
        return academicLoad + orgLoad + addLoad;

    }

    @Override
    public String toString() {
        return "Load : A.L. = " + academicLoad + " O.L. = " + orgLoad + " A.L. = " + addLoad;
    }
}
