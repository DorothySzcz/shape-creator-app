package pl.kurs.shapecreatorapp.factory.dto;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import pl.kurs.shapecreatorapp.model.Square;
import pl.kurs.shapecreatorapp.model.dto.ShapeDto;
import pl.kurs.shapecreatorapp.model.dto.SquareDto;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareDtoCreatorTest {

    private SquareDtoCreator squareDtoCreator;

    @Test
    void shouldReturnSquareDtoWhenShapeHasSquareType() {

        ModelMapper modelMapper = Mockito.mock(ModelMapper.class);
        squareDtoCreator = new SquareDtoCreator(modelMapper);

        Square square = new Square("SQUARE", 4.0);
        square.setId(1);
        square.setVersion(0);
        square.setCreatedBy("creator");
        square.setCreatedAt(LocalDateTime.now());
        square.setLastModifiedAt(LocalDateTime.now());
        square.setLastModifiedBy("creator");

        SquareDto squareDto = new SquareDto(1, "SQUARE", 4.0, 0, "creator",
                LocalDateTime.now(), LocalDateTime.now(), "creator", 19.63, 12.00);

        Mockito.when(modelMapper.map(square, SquareDto.class)).thenReturn(squareDto);
        ShapeDto result = squareDtoCreator.getShapeDto(square);

        assertEquals(squareDto, result);
    }
}