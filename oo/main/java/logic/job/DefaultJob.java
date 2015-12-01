package logic.job;

/**
 * Created by xlo on 15/11/29.
 * it's the default job
 */
public class DefaultJob implements Job {

    protected JobType jobType;

    public DefaultJob(JobType jobType) {
        this.jobType = jobType;
    }

    @Override
    public String getJobName() {
        if (this.jobType.equals(JobType.SOLIDER)) {
            return Job.solider;
        } else if (this.jobType.equals(JobType.KILLER)) {
            return Job.killer;
        } else if (this.jobType.equals(JobType.KNIGHT)) {
            return Job.knight;
        } else {
            return Job.normal;
        }
    }
}
