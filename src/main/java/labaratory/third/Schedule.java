package labaratory.third;

import java.util.Scanner;
import java.util.Random;

public class Schedule {
    private Processor processor;
    private MemoryManager memoryManager;
    private Process processes;
    private Random random = new Random();

    public Schedule() {
        processor = new Processor();
        memoryManager = new MemoryManager();
        processes = null;
    }

    /**
     * в этом методе, мы управляем страницами процесса, загрузкой их страниц в ОЗУ (да, придется пальчикам вбивать,
     * можете сделать автоматическое заполнение в ОЗУ тех страницы, у которых в 1 отмечен бит присутствия-отсутствия)
     * reset - сбросить бит модификации и чтения
     * @see MemoryManager#resetAllBits()
     * printing all - выведет содержимое ОЗУ, Swap и виртуальной памяти текущего процесса
     * @see MemoryManager#printPhysicalMemory()
     * @see MemoryManager#printSwapMemory()
     * @see MemoryManager#printVirtualMemoryOfCurrentProcess()
     * read - собственно операция чтения, сначала пишете read -> Enter -> индекс страницы в виртуальной памяти
     * @see Processor#read(int)
     * write  - тоже самое, только еще и значение свое какое-нибудь можете вписать
     * @see Processor#write(int, String)
     * continue - поменяет процесс
     * quit - закончит работу программы
     */
    public void start() {
        System.out.println("Commands: \n" +
                "1: reset - r\n" +
                "2: printing all - p\n" +
                "3: read - read\n" +
                "4: write - write\n" +
                "5: continue - continue\n" +
                "6: quit - quit");

        Process[] processes = new Process[random.nextInt(3)+1];
        try (Scanner scanner = new Scanner(System.in)) {
            for (Process process : processes) {
                process = new Process();
                process.setProcessName("Процесс: "+process.getId());
                process.run();
                Page[] refToVirtualMemoryOfCurrentProcess = process.getVirtualMemory();
                processor.setVirtualMemoryOfCurrentProcess(refToVirtualMemoryOfCurrentProcess);
                processor.setMemoryManager(memoryManager);
                memoryManager.setVirtualMemoryOfCurrentProcess(refToVirtualMemoryOfCurrentProcess);
                boolean condition = true;
                while (condition) {
                    switch (scanner.next()) {
                        case "r":
                            System.out.println("Reset bits of modification and read in the physical memory");
                            memoryManager.resetAllBits();
                            break;
                        case "p":
                            System.out.println("Printing all");
                            memoryManager.printVirtualMemoryOfCurrentProcess();
                            memoryManager.printPhysicalMemory();
                            memoryManager.printSwapMemory();
                            break;
                        case "read":
                            System.out.println("Operation read");
                            processor.read(scanner.nextInt());
                            break;
                        case "write":
                            System.out.println("Operation write");
                            processor.write(scanner.nextInt(), scanner.next());
                            break;
                        case "continue":
                             condition = false;
                             break;
                        case "quit":
                            return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Schedule().start();
    }
}
