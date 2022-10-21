package pl.kurs.shapecreatorapp.factory.dto;

import org.springframework.stereotype.Service;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.dto.CircleDto;
import pl.kurs.shapecreatorapp.model.dto.ShapeDto;

@Service
public class CircleDtoCreator implements ShapeDtoCreator {
    @Override
    public String getType() {
        return "CIRCLE";
    }

    @Override
    public ShapeDto find(Shape shape) {
        CircleDto circleDto = null;
        if (shape.getType().equals(getType())) {
            circleDto = new CircleDto();
        }
        return circleDto;
    }
}
