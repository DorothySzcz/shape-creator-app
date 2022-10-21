package pl.kurs.shapecreatorapp.factory.creators;

import org.springframework.stereotype.Service;
import pl.kurs.shapecreatorapp.exception.BadQuantityOfParametersException;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.Square;

import java.util.List;

@Service
public class SquareCreator implements ShapeCreator {

    @Override
    public String getType() {
        return "SQUARE";
    }

    @Override
    public Shape create(List<Double> parameters) {
        if (parameters.size() != 1) {
            throw new BadQuantityOfParametersException("BAD_QUANTITY_OF_PARAMETERS");
        }
        Square square = new Square();
        square.setType(getType());
        square.setWidth(parameters.get(0));
        square.setArea(square.calculateArea());
        square.setPerimeter(square.calculatePerimeter());
        return square;
    }
}
