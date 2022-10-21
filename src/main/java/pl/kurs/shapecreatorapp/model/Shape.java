package pl.kurs.shapecreatorapp.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Shape extends AuditableEntity {

    private String type;
    private Double area;
    private Double perimeter;

    public Shape() {
    }

    public Shape(String type, Double area, Double perimeter) {
        this.type = type;
        this.area = area;
        this.perimeter = perimeter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Shape shape = (Shape) o;
        return Objects.equals(type, shape.type) && Objects.equals(area, shape.area) && Objects.equals(perimeter, shape.perimeter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, area, perimeter);
    }

    public double calculateArea() {
        return 0;
    }

    public double calculatePerimeter() {
        return 0;
    }

}
