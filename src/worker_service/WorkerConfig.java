package worker_service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public final class WorkerConfig {
    private WorkerManagement manager;
    private Map<Integer, String> positionMap;

    public WorkerConfig(WorkerManagement manager) {
        this.manager = manager;
        positionMap = new HashMap<>();
        this.positionMap.put(1, "Операционист");
        this.positionMap.put(2, "Менеджер по кредитам");
    }

    public Worker registrateWorker(String name, String surname, int position) {
        Worker worker = new Worker();
        worker.setWorkerId();
        if (validateName(name)) {
            worker.setWorkerName(name);
        }

        if (validateSurname(surname)) {
            worker.setWorkerSurname(surname);
        }

        worker.setPosition(position);
        worker.setPositionName(this.positionMap.get(position));
        this.manager.addWorker(worker);
        return worker;
    }

    public void removeWorker(UUID id) {
        this.manager.removeWorker(id);
    }

    public Worker getWorker(UUID id) {
        return this.manager.getWorker(id);
    }

    private static boolean validateName(String name) {
        if (name.matches("[A-Z][a-z]*")) {
            return true;
        } else {
            throw new IllegalArgumentException("Incorrect name format (must contain 1-inf a-z characters, first capital");
        }
    }

    private static boolean validateSurname(String surname) {
        if (surname.matches("[A-Z][a-z]*")) {
            return true;
        } else {
            throw new IllegalArgumentException("Incorrect surname format (must contain 1-inf a-z characters, first capital");
        }
    }

    public void addWage(UUID workId, int money) {
        Worker worker = this.manager.getWorker(workId);
        worker.setWage(worker.getWage() + money);
    }

    public void printWorkersInfo() {
        Map<UUID, Worker> workerStorage = this.manager.getWorkerStorage();
        int iter = 1;

        for(Iterator var3 = workerStorage.entrySet().iterator(); var3.hasNext(); ++iter) {
            Entry entry = (Entry)var3.next();
            Worker iterWorker = (Worker)entry.getValue();
            System.out.println(String.format("\t%d. ID: %s, должность: %s, зарплата: %d", iter, iterWorker.getWorkerId(), iterWorker.getPositionName(), iterWorker.getWage()));
        }

        if (iter == 1) {
            System.out.println("Рабочие отсутствуют");
        }

    }
}