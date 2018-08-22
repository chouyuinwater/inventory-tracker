package com.jd.inventory.tracker.service;

import com.jd.inventory.tracker.dao.TemplateDao;
import com.jd.inventory.tracker.domain.Template;
import com.jd.inventory.tracker.domain.enums.ServiceStatusCodeEnum;
import com.jd.inventory.tracker.domain.enums.TemplateStatusEnum;
import com.jd.inventory.tracker.domain.vo.Page;
import com.jd.inventory.tracker.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TemplateService {

    @Autowired
    TemplateDao templateDao;

    public Template getTemplate(Template template) {
        return templateDao.get(template);
    }

    public List<Template> getTemplates(Page page, Template template) {
        return templateDao.gets(page, template);
    }

    @Transactional
    public boolean saveTemplate(Template template) {
        Template existTemplate = templateDao.get(template);
        if (existTemplate != null)
            throw new ServiceException(ServiceStatusCodeEnum.FAIL);

        template.setStatus(TemplateStatusEnum.VALID.getStatus());
        int num = templateDao.save(template);
        if (num != 1)
            throw new ServiceException(ServiceStatusCodeEnum.FAIL);
        return true;
    }

    @Transactional
    public boolean updateTemplate(Template template) {
        Template templateQuery = new Template();
        templateQuery.setId(template.getId());
        Template existTemplate = templateDao.get(templateQuery);
        if (existTemplate == null)
            throw new ServiceException(ServiceStatusCodeEnum.FAIL);

        if (!existTemplate.getTemplatename().equals(template.getTemplatename())) {
            templateQuery.setTemplatename(template.getTemplatename());
            templateQuery.setId(null);
            existTemplate = templateDao.get(templateQuery);
            if (existTemplate == null || !existTemplate.getSysid().equals(template.getSysid())) {
                int num = templateDao.update(template);
                if (num == 1)
                    return true;
            }
        }
        throw new ServiceException(ServiceStatusCodeEnum.FAIL);
    }
}
