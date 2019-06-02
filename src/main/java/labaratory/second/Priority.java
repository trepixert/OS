package labaratory.second;

public enum Priority {
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    private int priorityNumber;

    Priority(int priorityNumber) {
        this.priorityNumber = priorityNumber;
    }

    public static Priority getAttribute(int priorityNumber) {
        return Priority.values()[priorityNumber - 1];
    }

    public int getPriorityNumber() {
        return priorityNumber;
    }
}

