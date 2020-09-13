package worker_service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class WorkerManagement {
    private Map<UUID, Worker> workerStorage;

    public WorkerManagement() {
        workerStorage = new HashMap<>();
    }

    void addWorker(Worker worker) {
        this.workerStorage.put(worker.getWorkerId(), worker);
    }

    void removeWorker(UUID pid) {
        this.workerStorage.remove(pid);
    }

    Worker getWorker(UUID pid) {
        return (Worker)this.workerStorage.get(pid);
    }

    Map<UUID, Worker> getWorkerStorage() {
        return this.workerStorage;
    }
}