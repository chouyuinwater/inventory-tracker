package com.jd.inventory.tracker.domain.vo;

import java.util.List;

public class TrackerVo {
    private String eventno;

    private String sourceTemplate;
    private String targetTemplate;

    private InnerTracker sourceTracker;

    private InnerTracker targetTracker;

    public String getEventno() {
        return eventno;
    }

    public void setEventno(String eventno) {
        this.eventno = eventno;
    }

    public InnerTracker getSourceTracker() {
        return sourceTracker;
    }

    public void setSourceTracker(InnerTracker sourceTracker) {
        this.sourceTracker = sourceTracker;
    }

    public InnerTracker getTargetTracker() {
        return targetTracker;
    }

    public void setTargetTracker(InnerTracker targetTracker) {
        this.targetTracker = targetTracker;
    }

    public static class InnerTracker {
        private Integer currentStep;
        private List<InnerTemplate> innerTemplates;

        public Integer getCurrentStep() {
            return currentStep;
        }

        public void setCurrentStep(Integer currentStep) {
            this.currentStep = currentStep;
        }

        public List<InnerTemplate> getInnerTemplates() {
            return innerTemplates;
        }

        public void setInnerTemplates(List<InnerTemplate> innerTemplates) {
            this.innerTemplates = innerTemplates;
        }
    }

    public static class InnerTemplate {
        private String desc;
        private String status;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public String getSourceTemplate() {
        return sourceTemplate;
    }

    public void setSourceTemplate(String sourceTemplate) {
        this.sourceTemplate = sourceTemplate;
    }

    public String getTargetTemplate() {
        return targetTemplate;
    }

    public void setTargetTemplate(String targetTemplate) {
        this.targetTemplate = targetTemplate;
    }

}
