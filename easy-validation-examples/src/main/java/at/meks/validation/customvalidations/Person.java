package at.meks.validation.customvalidations;

public class Person {

    private String profession;

    private int activeMonthsInProfession;

    String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    int getActiveMonthsInProfession() {
        return activeMonthsInProfession;
    }

    public void setActiveMonthsInProfession(int activeMonthsInProfession) {
        this.activeMonthsInProfession = activeMonthsInProfession;
    }
}
