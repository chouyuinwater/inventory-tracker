package com.jd.inventory.tracker.service;

import com.jd.inventory.tracker.domain.TrackerLogExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jd.inventory.tracker.dao.TrackerLogExtDao;
@Component
public class ScheduledTasks {
    @Autowired
    TrackerLogExtDao trackerLogExtDao;
    @Autowired
    PusherService pusherService;
    private Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private int fastScheduleCount = 1;
    private int slowScheduleCount = 1;


    @Scheduled(fixedDelay = 15000)
    public void fastSchedule() {
        TrackerLogExt ext = trackerLogExtDao.get(new TrackerLogExt());
        boolean handled = pusherService.handleBacklog(ext);
        if(handled){
            ext.setYn(1);
            trackerLogExtDao.update(ext);
        }
        logger.info("fastScheduleCount run at () times", fastScheduleCount++);
    }

    @Scheduled(fixedRate = 7777777)
    public void slowSchedule() {
        logger.info("fastScheduleCount run at () times", slowScheduleCount++);
    }
}
