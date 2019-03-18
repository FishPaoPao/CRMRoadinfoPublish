package com.bytd.crm.roadinfo.entities;

public class DSubsSection {
    private Long id;

    private Long subsId;

    private Long sourceHighwayId;

    private Long sourceStationId;

    private Integer sourceStationDirection;

    private Long sourceStationMileage;

    private Long targetHighwayId;

    private Long targetStationId;

    private Integer targetStationDirection;

    private Long targetStationMileage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubsId() {
        return subsId;
    }

    public void setSubsId(Long subsId) {
        this.subsId = subsId;
    }

    public Long getSourceHighwayId() {
        return sourceHighwayId;
    }

    public void setSourceHighwayId(Long sourceHighwayId) {
        this.sourceHighwayId = sourceHighwayId;
    }

    public Long getSourceStationId() {
        return sourceStationId;
    }

    public void setSourceStationId(Long sourceStationId) {
        this.sourceStationId = sourceStationId;
    }

    public Integer getSourceStationDirection() {
        return sourceStationDirection;
    }

    public void setSourceStationDirection(Integer sourceStationDirection) {
        this.sourceStationDirection = sourceStationDirection;
    }

    public Long getSourceStationMileage() {
        return sourceStationMileage;
    }

    public void setSourceStationMileage(Long sourceStationMileage) {
        this.sourceStationMileage = sourceStationMileage;
    }

    public Long getTargetHighwayId() {
        return targetHighwayId;
    }

    public void setTargetHighwayId(Long targetHighwayId) {
        this.targetHighwayId = targetHighwayId;
    }

    public Long getTargetStationId() {
        return targetStationId;
    }

    public void setTargetStationId(Long targetStationId) {
        this.targetStationId = targetStationId;
    }

    public Integer getTargetStationDirection() {
        return targetStationDirection;
    }

    public void setTargetStationDirection(Integer targetStationDirection) {
        this.targetStationDirection = targetStationDirection;
    }

    public Long getTargetStationMileage() {
        return targetStationMileage;
    }

    public void setTargetStationMileage(Long targetStationMileage) {
        this.targetStationMileage = targetStationMileage;
    }
}