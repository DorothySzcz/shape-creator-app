package pl.kurs.shapecreatorapp.factory.creators;

import org.springframework.stereotype.Service;
import pl.kurs.shapecreatorapp.exception.BadQuantityOfParametersException;
import pl.kurs.shapecreatorapp.model.Circle;
import pl.kurs.shapecreatorapp.model.Shape;

import java.util.List;

@Service
public class CircleCreator implements ShapeCreator {

    @Override
    public String getType() {
        return "CIRCLE";
    }

    @Override
    public Shape create(List<Double> parameters) {
        if (parameters.size() != 1) {
            throw new BadQuantityOfParametersException("BAD_QUANTITY_OF_PARAMETERS");
        }
        Circle circle = new Circle();
        circle.setType(getType());
        circle.setRadius(parameters.get(0));
        circle.setArea(circle.calculateArea());
        circle.setPerimeter(circle.calculatePerimeter());
        return circle;
    }

}
