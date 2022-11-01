package pl.kurs.shapecreatorapp.factory.dto;

import org.springframework.stereotype.Service;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.dto.ShapeDto;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShapeDtoFactory {

    private final Map<String, ShapeDtoCreator> creators;

    public ShapeDtoFactory(Set<ShapeDtoCreator> creators) {
        this.creators = creators.stream().collect(Collectors.toMap(ShapeDtoCreator::getType, Function.identity()));
    }

    public ShapeDto findShapeDto(Shape shape) {
        return creators.get(shape.getType()).getShapeDto(shape);
    }

}
