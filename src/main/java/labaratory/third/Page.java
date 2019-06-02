package labaratory.third;

public class Page {
    private String processName;
    private int modificationBit;
    private int readOrWriteBit;
    private int indexAtPhysicalMemory;
    private int indexAtSwapMemory;
    private int presenceAndAbsenceBit;
    private String description;

    public Page() {
        modificationBit = 0;
        readOrWriteBit = 0;
        indexAtPhysicalMemory = -1;
        indexAtSwapMemory = -1;
        description = null;
    }

    public int getModificationBit() {
        return modificationBit;
    }

    public void setModificationBit(int modificationBit) {
        this.modificationBit = modificationBit;
    }

    public int getReadBit() {
        return readOrWriteBit;
    }

    public void setReadBit(int readOrWriteBit) {
        this.readOrWriteBit = readOrWriteBit;
    }

    public int getIndexAtPhysicalMemory() {
        return indexAtPhysicalMemory;
    }

    public void setIndexAtPhysicalMemory(int indexAtPhysicalMemory) {
        this.indexAtPhysicalMemory = indexAtPhysicalMemory;
    }

    public void setIndexAtSwapMemory(int indexAtSwapMemory) {
        this.indexAtSwapMemory = indexAtSwapMemory;
    }

    public int getPresenceAndAbsenceBit() {
        return presenceAndAbsenceBit;
    }

    public void setPresenceAndAbsenceBit(int presenceAndAbsenceBit) {
        this.presenceAndAbsenceBit = presenceAndAbsenceBit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @Override
    public String toString() {
        return "Page{" +
                "processName='" + processName + '\'' +
                ", modificationBit=" + modificationBit +
                ", readOrWriteBit=" + readOrWriteBit +
                ", indexAtPhysicalMemory=" + indexAtPhysicalMemory +
                ", indexAtSwapMemory=" + indexAtSwapMemory +
                ", presenceAndAbsenceBit=" + presenceAndAbsenceBit +
                ", description='" + description + '\'' +
                '}';
    }
}
