package labaratory.second;

import java.util.List;

public class Process {
    private int id;
    private Priority priority;
    private int time;
    private List<Thread> threads;
    private int timeForThread;
    private boolean isFinished;

    public Process() {
        id = 0;
        priority = null;
        time = 0;
        threads = null;
        timeForThread = 0;
        isFinished = false;
    }

    public Process(int id, Priority priority) {
        this.id = id;
        this.priority = priority;
        time = 0;
        threads = null;
        timeForThread = 0;
        isFinished = false;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    public Priority getPriority() {
        return priority;
    }

    public void startProcess() {
        if (isFinished) {
            return;
        }
        try {
            timeForThread = this.time / getSize();
            int sum = 0;
            System.out.println(toString() + " начал работу!");
            for (Thread thread : threads) {
                if (sum >= time)
                    break;
                sum += timeForThread;
                thread.setTimeOfProcess(timeForThread);
                if (!thread.run()) {
                    System.out.println(thread.toString() + " закончен не до конца!");
                }
            }
            System.out.println(toString() + " закончил работу!");
        } catch (ArithmeticException e) {
            isFinished = true;
        }
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("Процесс %d с приоритетом %s с выделенным %d временем",
                id, this.priority, time);
    }

    private int getSize() {
        int size = 0;
        for (Thread thread : threads) {
            if (thread.isFinished())
                continue;
            size++;
        }
        return size;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
