package pl.kurs.shapecreatorapp.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "shape")
public abstract class Shape extends AuditableEntity {

    private String type;

    public Shape() {
    }

    public Shape(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Shape shape = (Shape) o;
        return Objects.equals(type, shape.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    public double calculateArea() {
        return 0;
    }

    public double calculatePerimeter() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + "type='" + type + '\'';
    }
}
