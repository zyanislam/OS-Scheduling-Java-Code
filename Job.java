public class Job {
    private int jobID;
    private int startTime;
    private int actualStartTime;
    private int jobLength;
    private int remainingTime;

    public Job(int id, int start, int actualStart, int length) {
        jobID = id;
        startTime = start;
        actualStartTime = actualStart;
        jobLength = length;
        remainingTime = length;
    }

    public int getJobID() {
        return jobID;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(int start) {
        actualStartTime = start;
    }

    public int getJobLength() {
        return jobLength;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setRemainingTime(int time) {
        remainingTime = time;
    }

    @Override
    public String toString() {
        return "Job ID: " + jobID + ", Start Time: " + startTime
                + ", Actual Start Time: " + actualStartTime + ", Job Length: " + jobLength
                + ", Remaining Time: " + remainingTime;
    }
}