package pl.kurs.shapecreatorapp.factory.dto;

import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.dto.ShapeDto;

public interface ShapeDtoCreator {
    String getType();

    ShapeDto getShapeDto(Shape shape);
}
