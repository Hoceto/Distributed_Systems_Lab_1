package worker_service;

import java.util.UUID;

public final class Worker {
    private String workerName;
    private String workerSurname;
    private UUID workerId;
    private int wage;
    private int position;
    private String positionName;

    Worker() {
        this.wage = 0;
        this.positionName = "Операционист";
    }

    public String getWorkerName() {
        return this.workerName;
    }

    void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerSurname() {
        return this.workerSurname;
    }

    void setWorkerSurname(String workerSurname) {
        this.workerSurname = workerSurname;
    }

    public UUID getWorkerId() {
        return this.workerId;
    }

    void setWorkerId() {
        this.workerId = UUID.randomUUID();
    }

    public int getPosition() {
        return this.position;
    }

    void setPosition(int position) {
        this.position = position;
    }

    void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionName() {
        return this.positionName;
    }

    void setWage(int wage) {
        this.wage = wage;
    }

    int getWage() {
        return this.wage;
    }
}
