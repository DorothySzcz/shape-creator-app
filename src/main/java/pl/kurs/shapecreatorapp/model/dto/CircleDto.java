package pl.kurs.shapecreatorapp.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
public class CircleDto implements ShapeDto {

    private int id;
    private String type;
    private double radius;
    private int version;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedAt;
    private String lastModifiedBy;
    private double area;
    private double perimeter;

    public CircleDto() {
    }

    public CircleDto(int id, String type, double radius, int version, String createdBy, LocalDateTime createdAt, LocalDateTime lastModifiedAt, String lastModifiedBy, double area, double perimeter) {
        this.id = id;
        this.type = type;
        this.radius = radius;
        this.version = version;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.lastModifiedBy = lastModifiedBy;
        this.area = area;
        this.perimeter = perimeter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CircleDto circleDto = (CircleDto) o;
        return id == circleDto.id && Double.compare(circleDto.radius, radius) == 0 && version == circleDto.version && Double.compare(circleDto.area, area) == 0 && Double.compare(circleDto.perimeter, perimeter) == 0 && Objects.equals(type, circleDto.type) && Objects.equals(createdBy, circleDto.createdBy) && Objects.equals(createdAt, circleDto.createdAt) && Objects.equals(lastModifiedAt, circleDto.lastModifiedAt) && Objects.equals(lastModifiedBy, circleDto.lastModifiedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, radius, version, createdBy, createdAt, lastModifiedAt, lastModifiedBy, area, perimeter);
    }

    @Override
    public String toString() {
        return "CircleDto{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", radius=" + radius +
                ", version=" + version +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", lastModifiedAt=" + lastModifiedAt +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", area=" + area +
                ", perimeter=" + perimeter +
                '}';
    }
}
