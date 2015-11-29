package logic.job;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for job
 */
public class DefaultJobTest {

    @Test
    public void should_get_solider_when_is_solider() {
        assertEquals(Job.solider, new DefaultJob(JobType.SOLIDER).getJobName());
    }

    @Test
    public void should_get_normal_when_is_normal() {
        assertEquals(Job.normal, new DefaultJob(JobType.NORMAL).getJobName());
    }

}