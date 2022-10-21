package pl.kurs.shapecreatorapp.factory.dto;

import org.springframework.stereotype.Service;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.dto.ShapeDto;
import pl.kurs.shapecreatorapp.model.dto.SquareDto;

@Service
public class SquareDtoCreator implements ShapeDtoCreator {
    @Override
    public String getType() {
        return "SQUARE";
    }

    @Override
    public ShapeDto find(Shape shape) {
        SquareDto squareDto = null;
        if (shape.getType().equals(getType())) {
            squareDto = new SquareDto();
        }
        return squareDto;
    }
}
