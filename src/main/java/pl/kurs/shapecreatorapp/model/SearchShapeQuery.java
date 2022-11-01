package pl.kurs.shapecreatorapp.model;

import java.time.LocalDateTime;

public class SearchShapeQuery {

    private String type;
    private Double areaFrom;
    private Double areaTo;
    private Double perimeterFrom;
    private Double perimeterTo;
    private LocalDateTime createdFrom;
    private LocalDateTime createdTo;
    private String createdBy;
    private Double widthFrom;
    private Double widthTo;
    private Double heightFrom;
    private Double heightTo;
    private Double radiusFrom;
    private Double radiusTo;

    public SearchShapeQuery(String type, Double areaFrom, Double areaTo, Double perimeterFrom, Double perimeterTo,
                            LocalDateTime createdFrom, LocalDateTime createdTo, String createdBy, Double widthFrom,
                            Double widthTo, Double heightFrom, Double heightTo, Double radiusFrom, Double radiusTo) {
        this.type = type;
        this.areaFrom = areaFrom;
        this.areaTo = areaTo;
        this.perimeterFrom = perimeterFrom;
        this.perimeterTo = perimeterTo;
        this.createdFrom = createdFrom;
        this.createdTo = createdTo;
        this.createdBy = createdBy;
        this.widthFrom = widthFrom;
        this.widthTo = widthTo;
        this.heightFrom = heightFrom;
        this.heightTo = heightTo;
        this.radiusFrom = radiusFrom;
        this.radiusTo = radiusTo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAreaFrom() {
        return areaFrom;
    }

    public void setAreaFrom(Double areaFrom) {
        this.areaFrom = areaFrom;
    }

    public Double getAreaTo() {
        return areaTo;
    }

    public void setAreaTo(Double areaTo) {
        this.areaTo = areaTo;
    }

    public Double getPerimeterFrom() {
        return perimeterFrom;
    }

    public void setPerimeterFrom(Double perimeterFrom) {
        this.perimeterFrom = perimeterFrom;
    }

    public Double getPerimeterTo() {
        return perimeterTo;
    }

    public void setPerimeterTo(Double perimeterTo) {
        this.perimeterTo = perimeterTo;
    }

    public LocalDateTime getCreatedFrom() {
        return createdFrom;
    }

    public void setCreatedFrom(LocalDateTime createdFrom) {
        this.createdFrom = createdFrom;
    }

    public LocalDateTime getCreatedTo() {
        return createdTo;
    }

    public void setCreatedTo(LocalDateTime createdTo) {
        this.createdTo = createdTo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Double getWidthFrom() {
        return widthFrom;
    }

    public void setWidthFrom(Double widthFrom) {
        this.widthFrom = widthFrom;
    }

    public Double getWidthTo() {
        return widthTo;
    }

    public void setWidthTo(Double widthTo) {
        this.widthTo = widthTo;
    }

    public Double getHeightFrom() {
        return heightFrom;
    }

    public void setHeightFrom(Double heightFrom) {
        this.heightFrom = heightFrom;
    }

    public Double getHeightTo() {
        return heightTo;
    }

    public void setHeightTo(Double heightTo) {
        this.heightTo = heightTo;
    }

    public Double getRadiusFrom() {
        return radiusFrom;
    }

    public void setRadiusFrom(Double radiusFrom) {
        this.radiusFrom = radiusFrom;
    }

    public Double getRadiusTo() {
        return radiusTo;
    }

    public void setRadiusTo(Double radiusTo) {
        this.radiusTo = radiusTo;
    }
}
