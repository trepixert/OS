package labaratory.third;

import java.util.Random;

public class Process {
    private static int getId = 0;
    private int id;
    private Page[] virtualMemory;
    private int countOfNeededPages;
    private Random random = new Random();
    private String processName;

    public Process() {
        id = ++getId;
        virtualMemory = new Page[MemoryManager.RAM_SIZE / MemoryManager.PAGE_SIZE * 2];
        countOfNeededPages = random.nextInt(5)+3;
    }

    public Page[] getVirtualMemory() {
        return virtualMemory;
    }

    public void run(){
        fillVirtualMemory();
    }

    /**
     * Инициализация виртуальной памяти процесса
     */
    private void fillVirtualMemory() {
        for (int i = 0; i < virtualMemory.length; i++) {
            virtualMemory[i] = new Page();
            Page page = virtualMemory[i];
            page.setProcessName(processName);
            if (random.nextBoolean() && (countOfNeededPages > 0)) {
                page.setPresenceAndAbsenseBit(1);
                countOfNeededPages--;
            }
        }
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getId() {
        return id;
    }
}
