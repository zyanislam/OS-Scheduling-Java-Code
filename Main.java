import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String[] args) {
        run_srtf();
//        run_fcfs();
//        sjf();
    }
    private static void run_srtf() {
        float prob = 0.7F;
        int idCounter = 1;
        int Min = 2, Max = 6;
        boolean busy = false;
        Job currentJob = null;
        int executed = 0, shiftedTime = 0;
        int cpuUseTime = 0;

        long seed = 6;
        Random generator = new Random(seed);

        fcfs scheduler = new fcfs();

        // Make 4 Processes
//        Job j1 = new Job(1, 0, 0, 6);
//        Job j2 = new Job(2, 0, 0, 2);
//        Job j3 = new Job(3, 1, 0, 4);
//        Job j4 = new Job(4, 4, 0, 2);
//
//        //Add the Jobs into Scheduler
//        scheduler.addJob(j1);
//        scheduler.addJob(j2);
//        scheduler.addJob(j3);
//        scheduler.addJob(j4);

        // Create the Jobs beforehand
        for (int i=0; i<25; i++) {
            float toss = (float) generator.nextDouble();
            if (toss > prob) {
//                int toShift = currentJob.getStartTime() + currentJob.getJobLength() - i;
                int length = Min + (int) (generator.nextDouble() * ((Max - Min) + 1));
                Job newJob = new Job(idCounter++, i, i, length);
                scheduler.addJob(newJob);
                System.out.println(ANSI_BLUE + "Added job! , " + newJob + ANSI_RESET);
            }
        }

        // Execute the Jobs
        for (int i=0; i<25; i++) {
////        Create the jobs as you go
//            float toss = (float) generator.nextDouble();
//            if(toss > prob){
////                int toShift = currentJob.getStartTime() + currentJob.getJobLength() - i;
//                int length = Min + (int)(generator.nextDouble() * ((Max - Min) + 1));
//                Job newJob = new Job(idCounter++, i, i, length);
//                scheduler.addJob(newJob);
//                System.out.println(ANSI_BLUE + "Added job " + newJob + ANSI_RESET);
//            }

            System.out.println("Current Time Frame: " + i + "--" + (i+1));

            // Check if there is any Job in the queue
            if (!busy && scheduler.getJobsCount() != 0) {
                ArrayList<Job> jobs = scheduler.getJobs();
                Collections.sort(jobs, new JobComparator());
                currentJob = jobs.get(0);
                scheduler.removeJob(currentJob);
                currentJob.setActualStartTime(i);
                busy = true;
                System.out.println(ANSI_YELLOW + "Selected job " + ANSI_RESET);
                System.out.println(currentJob);
            }

            // If Busy increment CPU time
            if (busy) {
                cpuUseTime++;
                currentJob.setRemainingTime(currentJob.getRemainingTime() - 1);

                // Check if the Current Job is completed
                if (currentJob.getRemainingTime() == 0) {
                    System.out.println(ANSI_BLUE + "Job Done: " + currentJob.getJobID() + ANSI_RESET);
                    System.out.println();
//                    System.out.println(currentJob);
                    busy = false;
                    executed++;
                }

                // Check if there is any Job with less time remaining
                if (scheduler.getJobsCount() != 0 &&
                        scheduler.getJob().getRemainingTime() < currentJob.getRemainingTime()) {
                    System.out.println(ANSI_RED + "Job Preempted" + ANSI_RESET);
                    System.out.println();
                    scheduler.addJob(currentJob);
                    busy = false;
                    shiftedTime++;
                }
            }
        }

        // Print the results
        System.out.println("CPU Util: " + (float) cpuUseTime*100 / 25);
        System.out.println("Throughput: " + (float) executed*100 / 25);
    }

    private static void run_fcfs(){
        float prob = (float) 0.7F;
        int idCounter = 1;
        int Min = 2, Max = 6;
        boolean busy = false;
        Job currentJob = null;
        int executed = 0, shiftedTime = 0;
        int cpuUseTime = 0;

        long seed = 10;
        Random generator = new Random(seed);

        fcfs scheduler = new fcfs();
        for(int i=0; i<25; i++){
            System.out.println("Current time = " + i);
            if(currentJob != null){
                executed = i - currentJob.getStartTime();
                if (executed == currentJob.getJobLength()){
                    busy = false;
                    System.out.println("Turnaround time for job with ID = " + currentJob.getJobID()
                            + " is = " + (i-currentJob.getActualStartTime()));
                }
            }

            float toss = (float) generator.nextDouble();
            if (toss > prob){
                int length = Min + (int)(generator.nextDouble() * ((Max - Min) + 1));
                if(currentJob != null)
                    shiftedTime = currentJob.getJobLength() - executed;
                shiftedTime += scheduler.getTotalExecutionTime();
                Job j = new Job(idCounter++, i, i+shiftedTime, length);
                System.out.println("Added job ");
                System.out.println(j);
                scheduler.addJob(j);
            }

            if(!busy && scheduler.getJobsCount() != 0 && scheduler.getJob().getStartTime() == i){
                currentJob = scheduler.removeJob();
                System.out.println("Removed job");
                System.out.println(currentJob);
                busy = true;
            }

            if(busy){
                cpuUseTime++;
            }
        }

        double percentage = cpuUseTime * 1.0 / 25;
        System.out.println("Cpu utilization = " + percentage);
    }

    public static void sjf(){
        float prob = (float) 0.7F;
        int idCounter = 1;
        int minLength = 2, maxLength = 7;
        Job currentJob = null;
        boolean busy = false;

        long seed = 10;
        Random generator = new Random(seed);

        sjf scheduler = new sjf();

        for(int i =0; i<25; i++){
            System.out.println("Current time = " + i);

            if(currentJob != null && currentJob.getStartTime() + currentJob.getJobLength() == i){
                busy = false;
                System.out.println(ANSI_RED + "Removed job " + currentJob + ANSI_RESET);
            }

            float toss = (float) generator.nextDouble();
            if(toss > prob){
//                int toShift = currentJob.getStartTime() + currentJob.getJobLength() - i;
                int length = minLength + (int)(generator.nextDouble() * ((maxLength - minLength) + 1));
                Job newJob = new Job(idCounter++, i, i, length);
                scheduler.addToScheduler(newJob);
                System.out.println(ANSI_BLUE + "Added job " + newJob + ANSI_RESET);
            }


            if(!busy && scheduler.hasAnyJob()){
                currentJob = scheduler.getNextJob();
                currentJob.setStartTime(i);
                System.out.println(ANSI_YELLOW + "Current job is " + currentJob + ANSI_RESET);
                busy = true;
            }
        }


    }

}
