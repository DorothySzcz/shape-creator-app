package pl.kurs.shapecreatorapp.model;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Square extends Shape {
    private double width;

    public Square() {
    }

    public Square(String type, Double area, Double perimeter, double width) {
        super(type, area, perimeter);
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
        return Math.pow(width, 2);
    }

    @Override
    public double calculatePerimeter() {
        return 4 * width;
    }

}
