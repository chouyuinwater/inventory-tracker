package com.jd.inventory.tracker.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jd.inventory.tracker.dao.TemplateDao;
import com.jd.inventory.tracker.dao.TrackerDao;
import com.jd.inventory.tracker.domain.Template;
import com.jd.inventory.tracker.domain.Tracker;
import com.jd.inventory.tracker.domain.vo.Page;
import com.jd.inventory.tracker.domain.vo.TrackerVo;
import com.jd.inventory.tracker.domain.vo.TrackerVoQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrackerService {

    @Autowired
    TrackerDao trackerDao;

    @Autowired
    TemplateDao templateDao;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<TrackerVo> getTrackers(Page page, TrackerVoQuery trackerVoQuery) {
        Tracker trackerQuery = new Tracker();
        trackerQuery.setSysid(trackerVoQuery.getSourceSysid());
        trackerQuery.setSku(trackerVoQuery.getSku());
        List<Tracker> sourceTrackerList = trackerDao.gets(page, trackerQuery);

        Set<Long> templateSet = new HashSet<>();
        List<String> eventnoList = new ArrayList<>();
        for (int i = 0, len = sourceTrackerList.size(); i < len; i++) {
            Tracker t = sourceTrackerList.get(i);
            eventnoList.add(t.getEventno());
            templateSet.add(t.getTemplateid());
        }

        trackerQuery.setSysid(trackerVoQuery.getTargetSysid());
        trackerQuery.setEventnoList(eventnoList);
        List<Tracker> targetTrackerList = trackerDao.gets(page, trackerQuery);

        Map<String, Tracker> targetTrackerMap = new HashMap<>();
        for (int i = 0, len = targetTrackerList.size(); i < len; i++) {
            Tracker t = targetTrackerList.get(i);
            targetTrackerMap.put(t.getEventno(), t);
            templateSet.add(t.getTemplateid());
        }


        Template templateQuery = new Template();
        List templateQueryList = new ArrayList<>();
        templateQueryList.addAll(templateSet);
        templateQuery.setTemplateIds(templateQueryList);
        List<Template> templateList = templateDao.gets(null, templateQuery);
        Map<Long, Template> templateMap = new HashMap<>();
        for (int i = 0, len = templateList.size(); i < len; i++) {
            Template t = templateList.get(i);
            templateMap.put(t.getId(), t);
        }

        List<TrackerVo> trackerList = new ArrayList<>();

        for (int i = 0, len = sourceTrackerList.size(); i < len; i++) {
            TrackerVo to = new TrackerVo();

            Tracker sourceTracker = sourceTrackerList.get(i);
            Template sourceTemplate = templateMap.get(sourceTracker.getTemplateid());

            to.setEventno(sourceTracker.getEventno());

            TrackerVo.InnerTracker source = new TrackerVo.InnerTracker();
            fillTemplateData(sourceTemplate, source, sourceTracker);
            to.setSourceTracker(source);

            Tracker targetTracker = targetTrackerMap.get(sourceTracker.getEventno());
            to.setTargetTracker(null);
            if (targetTracker != null) {
                Template targetTemplate = templateMap.get(targetTracker.getTemplateid());
                TrackerVo.InnerTracker target = new TrackerVo.InnerTracker();
                fillTemplateData(targetTemplate, target, targetTracker);
                to.setTargetTracker(target);
            }
            trackerList.add(to);
        }

        return trackerList;
    }

    private void fillTemplateData(Template template, TrackerVo.InnerTracker ti, Tracker tracker) {
        String templateStr = template.getTemplate();
        List<TrackerVo.InnerTemplate> innerTemplateList = JSONObject.parseArray(templateStr, TrackerVo.InnerTemplate.class);
        ti.setInnerTemplates(innerTemplateList);
        for (int i = 0, len = innerTemplateList.size(); i < len; i++) {
            TrackerVo.InnerTemplate innerTemplate = innerTemplateList.get(i);
            if (innerTemplate.getStatus().equals(String.valueOf(tracker.getCurrentStatus()))) {
                ti.setCurrentStep(i + 1);
                return;
            }
        }
        ti.setCurrentStep(innerTemplateList.size());
    }
}
