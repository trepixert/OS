package labaratory.third;

import lombok.Data;

@Data
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
