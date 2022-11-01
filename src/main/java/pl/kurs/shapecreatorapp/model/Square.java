package pl.kurs.shapecreatorapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Objects;

@Entity
@Table(name = "square")
public class Square extends Shape {
    private double width;

    @Transient
    private double area;

    @Transient
    private double perimeter;

    public Square() {
    }

    public Square(String type, double width) {
        super(type);
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Square square = (Square) o;
        return Double.compare(square.width, width) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), width);
    }


    @Override
    public double calculateArea() {
        area = Math.pow(width, 2);
        return area;
    }

    @Override
    public double calculatePerimeter() {
        perimeter = 4 * width;
        return perimeter;
    }

    @Override
    public String toString() {
        return "Square{" + super.toString() +
                "width=" + width +
                ", area=" + area +
                ", perimeter=" + perimeter +
                '}';
    }
}
