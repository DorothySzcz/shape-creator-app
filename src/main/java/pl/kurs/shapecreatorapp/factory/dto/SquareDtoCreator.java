package pl.kurs.shapecreatorapp.factory.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.dto.SquareDto;

@Service
public class SquareDtoCreator implements ShapeDtoCreator {

    private ModelMapper modelMapper;

    public SquareDtoCreator(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public String getType() {
        return "SQUARE";
    }

    @Override
    public SquareDto getShapeDto(Shape shape) {
        SquareDto squareDto = null;
        if (shape.getType().equals(getType())) {
            squareDto = modelMapper.map(shape, SquareDto.class);
        }
        return squareDto;
    }
}
