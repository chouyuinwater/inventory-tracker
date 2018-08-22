package com.jd.inventory.tracker.controller;

import com.jd.inventory.tracker.domain.Template;
import com.jd.inventory.tracker.domain.enums.ServiceStatusCodeEnum;
import com.jd.inventory.tracker.domain.enums.TemplateStatusEnum;
import com.jd.inventory.tracker.domain.vo.GenericResult;
import com.jd.inventory.tracker.domain.vo.Page;
import com.jd.inventory.tracker.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    TemplateService templateService;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @RequestMapping(value = "/toTemplateList", method = RequestMethod.GET)
    public String toTemplateList() {
        return "template/templateList";
    }

    @ResponseBody
    @RequestMapping(value = "/getTemplates", method = RequestMethod.POST)
    public Page getTemplates(Page page, Template template) {
        logger.info("requestBody : page={},template={}", page);
        template.setStatus(TemplateStatusEnum.VALID.getStatus());

        List<Template> templateList = templateService.getTemplates(page, template);
        page.setRows(templateList);
        return page;
    }

    @RequestMapping(value = "/toAddTemplate", method = RequestMethod.GET)
    public String toAddTemplate() {
        return "template/editTemplate";
    }

    @RequestMapping(value = "/toEditTemplate", method = RequestMethod.GET)
    public String toEditTemplate(Template template, Map map) {
        logger.info("requestBody : template={}", template);
        template.setStatus(TemplateStatusEnum.VALID.getStatus());
        Template existTemplate = templateService.getTemplate(template);
        map.put("template", existTemplate);
        return "template/editTemplate";
    }


    @ResponseBody
    @RequestMapping(value = "/doEditTemplate", method = RequestMethod.POST)
    public GenericResult doEditTemplate(Template template) {
        logger.info("requestBody : template={}", template);
        if (template.getId() == null) {
            if (templateService.saveTemplate(template))
                return new GenericResult(ServiceStatusCodeEnum.SUCCESS);
        } else {
            if (templateService.updateTemplate(template))
                return new GenericResult(ServiceStatusCodeEnum.SUCCESS);
        }
        return new GenericResult(ServiceStatusCodeEnum.FAIL);
    }
}
