package com.jd.inventory.tracker.controller;

import com.jd.inventory.tracker.domain.TrackerLog;
import com.jd.inventory.tracker.domain.vo.Page;
import com.jd.inventory.tracker.domain.vo.TrackerLogVo;
import com.jd.inventory.tracker.domain.vo.TrackerVo;
import com.jd.inventory.tracker.domain.vo.TrackerVoQuery;
import com.jd.inventory.tracker.service.PusherService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author tdd
 */
@RestController
@RequestMapping("/trackerlog")
public class TrackerLogController {
    @Autowired
    PusherService pusherService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping(value = "/trackLogs")
    public ResponseEntity createCustomer(@RequestBody TrackerLogVo log) {
        pusherService.inputTrackLog(new TrackerLog(log),true);
        return new ResponseEntity(log, HttpStatus.OK);
    }

}
