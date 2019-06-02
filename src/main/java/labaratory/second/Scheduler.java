package labaratory.second;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Scheduler {
    public static final int TIME_FOR_PROCESS = 5;
    private Random random;
    private int processID;
    private int threadID;
    private List<Process> processes;

    public Scheduler() {
        processID = 0;
        threadID = 0;
        random = new Random();
        processes = new ArrayList<>();
    }

    public static void main(String[] args) {
        new Scheduler().run();
    }

    public void run() {
        Process[] processes = new Process[random.nextInt(5) + 1];
        for (Process process :
                processes) {
            Thread[] threads = new Thread[random.nextInt(3) + 1];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(++threadID, random.nextInt(5) + 1);
            }
            process = new Process(++processID, Priority.getAttribute((random.nextInt(3) + 1)));
            process.setTime(TIME_FOR_PROCESS * process.getPriority().getPriorityNumber());
            process.setThreads(Arrays.asList(threads));
            this.processes.add(process);
            threadID = 0;
        }
        while (true) {
            for (Process process : this.processes) {
                if (!process.isFinished()) {
                    process.startProcess();
                }
            }
            boolean check = true;
            for (Process process : this.processes) {
                if (!process.isFinished()) {
                    check = false;
                }
            }
            if (check) break;
        }
    }
}
