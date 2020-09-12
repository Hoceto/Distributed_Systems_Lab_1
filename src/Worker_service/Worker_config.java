package Worker_service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Worker_config {
    private Worker_management manager;
    private Map<Integer, String> position_map = new HashMap();
    private static int worker_count = 0;

    public Worker_config(Worker_management manager) {
        this.manager = manager;
        this.position_map.put(1, "Операционист");
        this.position_map.put(2, "Менеджер по кредитам");
    }

    public Worker registrateWorker(String name, String surname, int position) {
        Worker worker = new Worker();
        ++worker_count;
        worker.setPid(String.valueOf(worker_count));
        if (validateName(name)) {
            worker.setName(name);
        }

        if (validateSurname(surname)) {
            worker.setSurname(surname);
        }

        worker.setPosition(position);
        worker.setPosition_name((String)this.position_map.get(position));
        this.manager.addWorker(worker);
        return worker;
    }

    public void removeWorker(String id) {
        this.manager.removeWorker(id);
    }

    public Worker getWorker(String id) {
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

    public void addWage(String work_id, int money) {
        Worker worker = this.manager.getWorker(work_id);
        worker.setWage(worker.getWage() + money);
    }

    public void printWorkersInfo() {
        Map<String, Worker> worker_storage = this.manager.getWorker_storage();
        int iter = 1;

        for(Iterator var3 = worker_storage.entrySet().iterator(); var3.hasNext(); ++iter) {
            Entry<String, Worker> entry = (Entry)var3.next();
            Worker iter_worker = (Worker)entry.getValue();
            System.out.println(String.format("\t%d. ID: %s, должность: %s, зарплата: %d", iter, iter_worker.getPid(), iter_worker.getPosition_name(), iter_worker.getWage()));
        }

        if (iter == 1) {
            System.out.println("Рабочие отсутствуют");
        }

    }
}