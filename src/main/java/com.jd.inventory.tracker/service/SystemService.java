package com.jd.inventory.tracker.service;

import com.jd.inventory.tracker.dao.SystemDao;
import com.jd.inventory.tracker.domain.System;
import com.jd.inventory.tracker.domain.enums.ServiceStatusCodeEnum;
import com.jd.inventory.tracker.domain.enums.SystemStatusEnum;
import com.jd.inventory.tracker.domain.vo.Page;
import com.jd.inventory.tracker.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemService {

    @Autowired
    SystemDao systemDao;

    public System getSystem(System system) {
        return systemDao.get(system);
    }

    public List<System> getSystems(Page page, System system) {
        return systemDao.gets(page, system);
    }

    @Transactional
    public boolean saveSystem(System system) {
        System existSystem = systemDao.get(system);
        if (existSystem != null)
            throw new ServiceException(ServiceStatusCodeEnum.FAIL);

        system.setStatus(SystemStatusEnum.VALID.getStatus());
        int num = systemDao.save(system);
        if (num != 1)
            throw new ServiceException(ServiceStatusCodeEnum.FAIL);
        return true;
    }

    @Transactional
    public boolean updateSystem(System system) {
        System systemQuery = new System();
        systemQuery.setId(system.getId());
        System existSystem = systemDao.get(systemQuery);
        if (existSystem == null)
            throw new ServiceException(ServiceStatusCodeEnum.FAIL);

        if(!existSystem.getSysname().equals(system.getSysname())){
            systemQuery.setSysname(system.getSysname());
            systemQuery.setId(null);
            existSystem = systemDao.get(systemQuery);
            if (existSystem != null)
                throw new ServiceException(ServiceStatusCodeEnum.FAIL);
        }

        int num = systemDao.update(system);
        if (num != 1)
            throw new ServiceException(ServiceStatusCodeEnum.FAIL);

        return true;
    }
}
