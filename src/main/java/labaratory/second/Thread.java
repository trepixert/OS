package labaratory.second;

class Thread {
    private int id;
    private int time;
    private int timeOfProcess;
    private boolean isFinished;

    Thread(int id, int time) {
        this.id = id;
        this.time = time;
        isFinished = false;
    }

    public boolean run() {
        if (isFinished) {
            return true;
        }
        for (int i = 0; i < timeOfProcess; i++) {
            if (time == 0) {
                System.out.println(toString());
                isFinished = true;
                return true;
            }
            System.out.println("Поток " + id + ", осталось времени: " + time--);
        }
        return false;
    }

    public void setTimeOfProcess(int timeOfProcess) {
        if (timeOfProcess == 0) {
            this.timeOfProcess = 3;
            return;
        }
        this.timeOfProcess = timeOfProcess;
    }

    @Override
    public String toString() {
        return String.format("Поток %d завершил работу", id);
    }

    public boolean isFinished() {
        return isFinished;
    }
}
