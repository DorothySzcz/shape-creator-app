package pl.kurs.shapecreatorapp.factory.dto;

import org.springframework.stereotype.Service;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.dto.RectangleDto;
import pl.kurs.shapecreatorapp.model.dto.ShapeDto;

@Service
public class RectangleDtoCreator implements ShapeDtoCreator {
    @Override
    public String getType() {
        return "RECTANGLE";
    }

    @Override
    public ShapeDto find(Shape shape) {
        RectangleDto rectangleDto = null;
        if (shape.getType().equals(getType())) {
            rectangleDto = new RectangleDto();
        }
        return rectangleDto;
    }
}
