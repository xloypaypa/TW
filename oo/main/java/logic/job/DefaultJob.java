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
        } else if (this.jobType.equals(JobType.STALKER)) {
            return Job.stalker;
        } else if (this.jobType.equals(JobType.PALADIN)) {
            return Job.paladin;
        } else {
            return Job.normal;
        }
    }
}
