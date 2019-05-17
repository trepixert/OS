package labaratory.third;

public class Processor {
    private Page[] virtualMemoryOfCurrentProcess;
    private MemoryManager memoryManager;

    public Processor(){
        virtualMemoryOfCurrentProcess = null;
    }

    public void setVirtualMemoryOfCurrentProcess(Page[] virtualMemoryOfCurrentProcess) {
        this.virtualMemoryOfCurrentProcess = virtualMemoryOfCurrentProcess;
    }

    /**
     * Операция чтения. Эти операции придется вбивать ручками в
     * @see Schedule#start()
     * Ищет страницу по переданному индексу, проверяет бит присутствия и отсутствия и индекс в ОЗУ
     * (ибо, когда процесс инициализируется, то там рандомным образом некоторым страницам устанавливается бит присутствия и
     * отсутствия)
     * передает управление менеджеру и передает в качестве параметра индекс виртуальной страницы, где
     * менеджер памяти либо загрузит эту страницу
     * @see MemoryManager#setToPhysicalMemory(int)
     * , если страницы еще нет в ОЗУ, либо просто
     * установит бит чтения в 1 (нужно для последующей работы алгоритма замещения)
     * @param indexOfVirtualMemory индекс страницы из виртуальной памяти
     * @throws Exception если места в озу и файле подкачки нет, для загрузки страницы в ОЗУ, то выкинет исключение
     */

    public void read(int indexOfVirtualMemory) throws Exception{
        Page page = virtualMemoryOfCurrentProcess[indexOfVirtualMemory];
        if (page.getPresenceAndAbsenseBit()==1) {
            if(page.getIndexAtPhysicalMemory()== -1){
                memoryManager.setToPhysicalMemory(indexOfVirtualMemory);
            }
            page.setReadBit(1);
            System.out.println(page.getDescription());
        } else {
            memoryManager.setToPhysicalMemory(indexOfVirtualMemory);
            read(indexOfVirtualMemory);
        }
    }

    /**
     * Аналогично операции чтения, только здесь вместо бита чтения, устанавливается бит модифицации, ну и значение какое-нибудь
     * @param indexOfVirtualMemory индекс страницы в виртуальной памяти
     * @param description введенное значение
     * @throws Exception если места в озу и файле подкачки нет, для загрузки страницы в ОЗУ, то выкинет исключение
     */
    public void write(int indexOfVirtualMemory, String description) throws Exception {
        Page page = virtualMemoryOfCurrentProcess[indexOfVirtualMemory];
        if (page.getPresenceAndAbsenseBit()==1) {
            if(page.getIndexAtPhysicalMemory()== -1){
                memoryManager.setToPhysicalMemory(indexOfVirtualMemory);
            }
            page.setModificationBit(1);
            page.setDescription(description);
        } else {
            memoryManager.setVirtualMemoryOfCurrentProcess(virtualMemoryOfCurrentProcess);
            memoryManager.setToPhysicalMemory(indexOfVirtualMemory);
            write(indexOfVirtualMemory,description);
        }
    }

    public void setMemoryManager(MemoryManager memoryManager) {
        this.memoryManager = memoryManager;
    }
}
