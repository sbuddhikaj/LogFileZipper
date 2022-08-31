/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logfilezipperbackground;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author buddhika_j
 */
public class RunEveryDay implements Runnable{

    public static void runtask(Long runat,Long firstdelay) {
       
        Long delayTime;

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Long initialDelay = LocalDateTime.now().until(LocalDate.now().plusDays(1).atTime(00, 00), ChronoUnit.MINUTES);

        if (initialDelay > TimeUnit.DAYS.toMinutes(1)) {
            delayTime = LocalDateTime.now().until(LocalDate.now().atTime(00, 00), ChronoUnit.MINUTES);
        } else {
            delayTime = initialDelay;
        }
        
         Long runin =runat;// TimeUnit.DAYS.toMinutes(1);
              
         if(firstdelay==0){
             delayTime=Long.parseLong("0");
         }
         
        scheduler.scheduleAtFixedRate(new RunEveryDay(), delayTime,runin, TimeUnit.MINUTES);
        
        System.out.println("delayTime : " +delayTime);
        System.out.println("Run In every : " +runin +" Minutes");
        System.out.println("schedule Started");
    }

    @Override
    public void run() {
        LogFileZipperBackground.zipfile();
        System.out.println("I am Running at:" + new Date());
    }
}