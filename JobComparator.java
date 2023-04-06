import java.util.Comparator;

public class JobComparator implements Comparator<Job> {
    @Override
    public int compare(Job o1, Job o2) {
        if (o1.getRemainingTime() < o2.getRemainingTime())
            return o1.getJobID();
        return o2.getJobID();
    }
}