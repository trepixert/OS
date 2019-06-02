package labaratory.fourth;

public class File {

    private String fileName;

    private MemoryBlock startIndex;

    private int size;

    public File(String name, int size) {
        fileName = name;
        this.size = size;
    }

    public MemoryBlock getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(MemoryBlock startIndex) {
        this.startIndex = startIndex;
    }

    public String getFileName() {
        return fileName;
    }

    public int getSize() {
        return size;
    }
}
