package com.jd.inventory.tracker.controller;

import com.jd.inventory.tracker.domain.vo.Page;
import com.jd.inventory.tracker.domain.vo.TrackerVo;
import com.jd.inventory.tracker.domain.vo.TrackerVoQuery;
import com.jd.inventory.tracker.service.TrackerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/tracker")
public class TrackerController {

    @Autowired
    TrackerService trackerService;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @RequestMapping(value = "/toTrackerList", method = RequestMethod.GET)
    public String toTrackerList() {
        return "tracker/trackerList";
    }

    @ResponseBody
    @RequestMapping(value = "/getTrackers", method = RequestMethod.POST)
    public Page getTrackers(Page page, TrackerVoQuery trackerQuery) {
        if(trackerQuery==null || StringUtils.isEmpty(trackerQuery.getSku())){
            page.setRows(Collections.EMPTY_LIST);
            return page;
        }
        logger.info("requestBody : page={},trackerQuery={}", page, trackerQuery);
        List<TrackerVo> trackerList = trackerService.getTrackers(page, trackerQuery);
        page.setRows(trackerList);
        return page;
    }
}
