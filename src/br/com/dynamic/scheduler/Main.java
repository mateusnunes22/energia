package br.com.dynamic.scheduler;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
 
/**
 *
 * @author dcn
 */
public class Main {
 
    /**
     * @param args the command line arguments
     */
    public void main() {
 
        Trigger trigger = TriggerUtils.makeSecondlyTrigger( 1 );
        trigger.setName( "Test Trigger" );
        
        Trigger trigger2 = TriggerUtils.makeSecondlyTrigger( 1 );
        trigger2.setName( "Broad Trigger" );
 
        JobDetail jobDetail = new JobDetail( "TestJob", "TestJob Group", TestJob.class );
        JobDetail job2Detail = new JobDetail( "ComunicacaoBroadcast", "TestJob Group", ComunicacaoBroadcast.class );
 
        Scheduler scheduler;
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            //scheduler.scheduleJob( jobDetail, trigger );
            //scheduler.scheduleJob( job2Detail, trigger2 );
            //scheduler.start();
        } catch (SchedulerException ex) {
            //ex.printStackTrace();
        }
    }
}