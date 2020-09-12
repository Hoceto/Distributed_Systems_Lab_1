package Worker_service;

import java.util.HashMap;
import java.util.Map;

public class Worker_management {
    private Map<String, Worker> worker_storage = new HashMap();

    public Worker_management() {
    }

    public void addWorker(Worker worker) {
        this.worker_storage.put(worker.getPid(), worker);
    }

    public void removeWorker(String pid) {
        this.worker_storage.remove(pid);
    }

    public Worker getWorker(String pid) {
        return (Worker)this.worker_storage.get(pid);
    }

    Map<String, Worker> getWorker_storage() {
        return this.worker_storage;
    }
}