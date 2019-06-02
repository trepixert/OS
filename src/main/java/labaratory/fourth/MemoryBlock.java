package labaratory.fourth;

class MemoryBlock {

    private MemoryBlockStatus state;
    private MemoryBlock next;

    public MemoryBlock(MemoryBlock next) {
        this.next = next;

        if (next == null) {
            state = MemoryBlockStatus.FREE;
        } else {
            state = MemoryBlockStatus.BUSY;
        }
    }

    public MemoryBlockStatus getState() {
        return state;
    }

    public void setState(MemoryBlockStatus state) {
        this.state = state;
    }

    public MemoryBlock getNext() {
        return next;
    }

    public void setNext(MemoryBlock next) {
        this.next = next;
    }

    public boolean isEnd() {
        return next == null;
    }

}
