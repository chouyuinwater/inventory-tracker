package com.jd.inventory.tracker.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jd.inventory.tracker.dao.TemplateDao;
import com.jd.inventory.tracker.dao.TrackerDao;
import com.jd.inventory.tracker.dao.TrackerLogDao;
import com.jd.inventory.tracker.dao.TrackerLogExtDao;
import com.jd.inventory.tracker.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分析流水，结算主表状态，
 * @author tdd
 */
@Service
public class PusherService {


    @Autowired
    TrackerDao trackerDao;
    @Autowired
    TrackerLogDao trackerLogDao;
    @Autowired
    TrackerLogExtDao trackerLogExtDao;
    @Autowired
    TemplateService templateService;
    @Autowired
    TemplateDao templateDao;

    private Map<String,Integer> stepIndex = new HashMap<String,Integer>();
    private Map<String,Integer> maxStep = new HashMap<String,Integer>();
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void PusherService(){
        List<Template> templates = templateService.getAllTemplates();

        for (Template template:templates) {
            ObjectMapper mapper = new ObjectMapper();

            TypeReference<List<Step>> mapType = new TypeReference<List<Step>>() {};
            List<Step> steps = new ArrayList<>();
            try {
                steps = mapper.readValue(template.getTemplate(), mapType);
            }
            catch(Exception ex){
                logger.error(ex.toString(), ex);
            }

            //Print list of person objects output using Java 8
            steps.forEach(System.out::println);

            int i = 1;
            for(Step step:steps){
                stepIndex.put(template.getStepKey() + step.getStatus(), i++);
            }
            maxStep.put(template.getStepKey(), i-1);
        }

    }
    public boolean inputTrackLog(TrackerLog log, boolean saveLog){
        boolean handled = false;
        Tracker tracker = new Tracker();
        tracker.setSku(log.getSku());
        tracker.setSysid(log.getSysid());
        tracker.setTemplateid(log.getTemplateid());
        tracker.setEventno(log.getEventno());
        Integer curStep = stepIndex.get(log.getStepKey());
        int preStep = curStep == null? -1 : curStep-1;
        if(preStep>0){
            tracker.setCurrentStep(preStep);
            Tracker t = trackerDao.get(tracker);
            if(t==null && saveLog){
                // TODO: throw into backlog table
                trackerLogExtDao.save(new TrackerLogExt(log));
            }
            else{
                t.setCurrentStep(curStep);
                // if next step is null, end state
                if(curStep == maxStep.get(log.getStepKey())){
                    //TODO:t.setStatus(1);
                    t.setEndStatus(1);
                }
                tracker.setEndTime(log.getCreateTime());
                //成功处理
                trackerDao.update(t);
                handled = true;
            }
        }
        else{
            // first step
            //TODO:t.setStep(1);
            tracker.setEndTime(log.getCreateTime());
            trackerDao.save(tracker);
            handled = true;
        }
        if(saveLog){
            trackerLogDao.save(log);
        }
        return handled;
    }

    public boolean handleBacklog(TrackerLogExt ext){
        if(ext==null)
            return false;
        TrackerLog log = new TrackerLog(ext);
        return inputTrackLog(log, false);
    }
}
