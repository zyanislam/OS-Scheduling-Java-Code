import java.util.PriorityQueue;

public class sjf {
    private PriorityQueue<Job> scheduler = null;

    public sjf() {
        JobComparator jc = new JobComparator();
        this.scheduler = new PriorityQueue<Job>(1, jc);
    }

    public void addToScheduler(Job j) {
        this.scheduler.add(j);
    }

    public boolean hasAnyJob() {
        return !this.scheduler.isEmpty();
    }

    public Job getNextJob() {
        return this.scheduler.remove();
    }
}
