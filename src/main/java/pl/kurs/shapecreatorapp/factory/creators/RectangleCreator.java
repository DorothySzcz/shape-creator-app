package pl.kurs.shapecreatorapp.factory.creators;

import org.springframework.stereotype.Service;
import pl.kurs.shapecreatorapp.exception.BadQuantityOfParametersException;
import pl.kurs.shapecreatorapp.model.Rectangle;
import pl.kurs.shapecreatorapp.model.Shape;

import java.util.List;

@Service
public class RectangleCreator implements ShapeCreator {

    @Override
    public String getType() {
        return "RECTANGLE";
    }

    @Override
    public Shape create(List parameters) {
        if (parameters.size() != 2) {
            throw new BadQuantityOfParametersException("BAD_QUANTITY_OF_PARAMETERS");
        }

        Rectangle rectangle = new Rectangle();
        rectangle.setType(getType());
        rectangle.setWidth((Double) parameters.get(0));
        rectangle.setHeight((Double) parameters.get(1));
        rectangle.calculateArea();
        rectangle.calculatePerimeter();
        return rectangle;
    }
}
