package pl.kurs.shapecreatorapp.factory.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.dto.RectangleDto;

@Service
public class RectangleDtoCreator implements ShapeDtoCreator {

    private final ModelMapper modelMapper;

    public RectangleDtoCreator(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public String getType() {
        return "RECTANGLE";
    }

    @Override
    public RectangleDto getShapeDto(Shape shape) {
        RectangleDto rectangleDto = null;
        if (shape.getType().equals(getType())) {
            rectangleDto = modelMapper.map(shape, RectangleDto.class);
        }
        return rectangleDto;
    }
}
