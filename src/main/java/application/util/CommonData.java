package application.util;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CommonData {
    private JsonFileReader fileReader = new JsonFileReader();

    private double baseRate;

    private double industryAllowance = 0.06;

    public CommonData() {
        init();
    }

    private Map<Integer,Double> tariffsByCategory = new HashMap<>();

    private Map<String, Double> experienceAllowances = new HashMap<>();

    private Map<String,Double> youngSpecialistsAllowances = new HashMap<>();

    private Map<String, Double> qualificationAllowances = new HashMap<>();

    // Получение надбавки по разряду
    public double getTariffByCategory(int category) throws NullPointerException{
        return tariffsByCategory.get(category);
    }
    // Получение надбавки по стажу
    public double getAllowanceByExperience(String experience) throws NullPointerException{
        try {
            return experienceAllowances.get(experience);
        }
        catch (NullPointerException e){
            System.out.println("Exp exception");
            return 0;
        }

    }
    // Получение надбавки за квалификацию
    public double getAllowanceByQualification(String qualification) throws NullPointerException{
        try {
            return qualificationAllowances.get(qualification);
        }
        catch (NullPointerException e){
            System.out.println("Qual exception");
            return 0;
        }
    }
    // Получение надбавки молодого специалиста
    public double getYSAllowances(String specialistType) throws NullPointerException{
        try {
            return youngSpecialistsAllowances.get(specialistType);
        }
        catch (NullPointerException e){
            return 0;
        }

    }

    public double getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(double baseRate) {
        this.baseRate = baseRate;
    }

    public double getIndustryAllowance() {
        return industryAllowance;
    }

    public void setIndustryAllowance(double industryAllowance) {
        this.industryAllowance = industryAllowance;
    }

    public Map<Integer, Double> getTariffsByCategory() {
        return tariffsByCategory;
    }

    public void setTariffsByCategory(Map<Integer, Double> tariffsByCategory) {
        this.tariffsByCategory = tariffsByCategory;
    }

    public Map<String, Double> getExperienceAllowances() {
        return experienceAllowances;
    }

    public void setExperienceAllowances(Map<String, Double> experienceAllowances) {
        this.experienceAllowances = experienceAllowances;
    }

    public Map<String, Double> getYoungSpecialistsAllowances() {
        return youngSpecialistsAllowances;
    }

    public void setYoungSpecialistsAllowances(Map<String, Double> youngSpecialistsAllowances) {
        this.youngSpecialistsAllowances = youngSpecialistsAllowances;
    }

    public Map<String, Double> getQualificationAllowances() {
        return qualificationAllowances;
    }

    public void setQualificationAllowances(Map<String, Double> qualificationAllowances) {
        this.qualificationAllowances = qualificationAllowances;
    }

    private void init(){
        JSONObject jsonObj = fileReader.readJsonFile("src\\main\\resources\\files\\options.json");
        baseRate = jsonObj.getDouble("base_rate");
        tariffsByCategory.put(7,1.47);
        tariffsByCategory.put(8,1.57);
        tariffsByCategory.put(9,1.68);
        tariffsByCategory.put(10,1.79);
        tariffsByCategory.put(11,1.91);

        experienceAllowances.put("до 5 лет",0.1);
        experienceAllowances.put("5-10 лет",0.15);
        experienceAllowances.put("10-15 лет",0.20);
        experienceAllowances.put("cв. 15 лет",0.30);

        youngSpecialistsAllowances.put("Одарённый", 0.45);
        youngSpecialistsAllowances.put("Молодой специалист", 0.3);

        qualificationAllowances.put("б/к", 0.3);
        qualificationAllowances.put("2-я к.к.", 0.4);
        qualificationAllowances.put("1-я к.к.", 0.5);
        qualificationAllowances.put("в.к.к.", 0.65);
        qualificationAllowances.put("уч.-методист", 0.80);
    }
}
