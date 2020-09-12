package Worker_service;

public class Worker {
    private String name;
    private String surname;
    private String pid;
    private boolean isPersonal = true;
    private int wage = 0;
    private int position;
    private String position_name = "Операционист";

    public Worker() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getPosition_name() {
        return this.position_name;
    }

    public boolean isPersonal() {
        return this.isPersonal;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public int getWage() {
        return this.wage;
    }
}
