package com.jd.inventory.tracker.dao;

import com.jd.inventory.tracker.domain.Template;

import java.util.List;

public interface TemplateDao extends BaseDao<Template, Template> {
    List<Template> getAll();
}
