package labaratory.fourth;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class PhysicalMemoryTable {
    private static PhysicalMemoryTable instance = new PhysicalMemoryTable();
    private final int CAPACITY = 40;
    private final int BLOCK_SIZE = 4;
    private int numberOfFreeBlocks;

    private ArrayList<MemoryBlock> memoryBlocks;
    private ArrayList<File> files;

    private PhysicalMemoryTable() {

        files = new ArrayList<>();

        memoryBlocks = new ArrayList<>();

        numberOfFreeBlocks = CAPACITY / BLOCK_SIZE;
        IntStream.range(0, CAPACITY / BLOCK_SIZE).forEach(i -> memoryBlocks.add(new MemoryBlock(null)));
    }

    public static PhysicalMemoryTable getInstance() {
        return instance;
    }

    public ArrayList<MemoryBlock> getMemoryBlocks() {
        return memoryBlocks;
    }

    public int getDiskSize() {
        return CAPACITY;
    }

    public int getBlockSize() {
        return BLOCK_SIZE;
    }

    public int getCountBlocks() {
        return memoryBlocks.size();
    }

    public void addFile(File file) throws Exception {
        int fileBlocks = (file.getSize() / BLOCK_SIZE) + (file.getSize() % BLOCK_SIZE != 0 ? 1 : 0);

        if (numberOfFreeBlocks < fileBlocks) {
            throw new Exception("Недостаточно места на диске");
        }

        numberOfFreeBlocks -= fileBlocks;

        int addedBlocks = 0;
        MemoryBlock lastAddedBlock = null;
        for (int i = 0; i < memoryBlocks.size(); i++) {

            if (addedBlocks == fileBlocks) {
                break;
            }

            if (memoryBlocks.get(i).getState() == MemoryBlockStatus.FREE) {

                if (addedBlocks == 0) {
                    for (var f : files) {
                        if (f.getFileName().equals(file.getFileName())) {
                            throw new Exception("Уже есть такой файл");
                        }
                    }
                    file.setStartIndex(memoryBlocks.get(i));
                    files.add(file);
                } else {
                    if (lastAddedBlock != null) {
                        lastAddedBlock.setNext(memoryBlocks.get(i));
                    }
                }

                memoryBlocks.get(i).setState(MemoryBlockStatus.BUSY);

                lastAddedBlock = memoryBlocks.get(i);
                addedBlocks++;
            }
        }
    }

    public void deleteFile(String name) {

        File curFile = null;

        for (var file : files) {
            if (file.getFileName().equals(name)) {
                curFile = file;
            }
        }

        files.remove(curFile);

        MemoryBlock index = curFile.getStartIndex();

        while (true) {
            MemoryBlock next = index.getNext();
            index.setState(MemoryBlockStatus.FREE);
            index.setNext(null);
            if (next == null) {
                break;
            }
            numberOfFreeBlocks++;
            index = next;
        }
    }

    public void selectFile(String name) {

        File curFile = null;

        for (var file : files) {
            if (file.getFileName().equals(name)) {
                curFile = file;
            }
        }

        for (int i = 0; i < memoryBlocks.size(); i++) {
            if (memoryBlocks.get(i).getState() == MemoryBlockStatus.SELECTED) {
                memoryBlocks.get(i).setState(MemoryBlockStatus.BUSY);
            }
        }

        MemoryBlock index = curFile.getStartIndex();
        while (true) {
            index.setState(MemoryBlockStatus.SELECTED);
            if (index.isEnd()) {
                break;
            }
            index = index.getNext();
        }
    }
}
