import java.util.ArrayList;
import java.util.Collections;
public class fcfs {
    private ArrayList<Job> jobs = null;

    public fcfs() {
        jobs = new ArrayList<>(0);
    }

    public void addJob(Job j) {
        jobs.add(j);
        Collections.sort(jobs, new JobComparator());
    }

    public void removeJob(Job j) {
        jobs.remove(j);
    }
    public Job removeJob() {
        return jobs.remove(0);
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public Job getJob() {
        return jobs.get(0);
    }

    public int getJobsCount() {
        return jobs.size();
    }

    public int getTotalExecutionTime() {
        int time = 0;
        if (this.jobs != null) {
            for (int i = 0; i < jobs.size(); i++) {
                time += jobs.get(i).getJobLength();
            }
        }

        return time;
    }
}