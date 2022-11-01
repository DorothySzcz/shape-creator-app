package pl.kurs.shapecreatorapp.factory.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.dto.CircleDto;

@Service
public class CircleDtoCreator implements ShapeDtoCreator {

    private ModelMapper modelMapper;

    public CircleDtoCreator(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public String getType() {
        return "CIRCLE";
    }

    @Override
    public CircleDto getShapeDto(Shape shape) {
        CircleDto circleDto = null;
        if (shape.getType().equals(getType())) {
            circleDto = modelMapper.map(shape, CircleDto.class);
        }
        return circleDto;
    }
}
