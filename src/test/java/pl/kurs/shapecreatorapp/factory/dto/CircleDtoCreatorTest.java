package pl.kurs.shapecreatorapp.factory.dto;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import pl.kurs.shapecreatorapp.model.Circle;
import pl.kurs.shapecreatorapp.model.dto.CircleDto;
import pl.kurs.shapecreatorapp.model.dto.ShapeDto;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircleDtoCreatorTest {

    private CircleDtoCreator circleDtoCreator;

    @Test
    void shouldReturnCircleDtoWhenShapeHasCircleType() {

        ModelMapper modelMapper = Mockito.mock(ModelMapper.class);
        circleDtoCreator = new CircleDtoCreator(modelMapper);

        Circle circle = new Circle("CIRCLE", 2.5);
        circle.setId(1);
        circle.setVersion(0);
        circle.setCreatedBy("creator");
        circle.setCreatedAt(LocalDateTime.now());
        circle.setLastModifiedAt(LocalDateTime.now());
        circle.setLastModifiedBy("creator");

        CircleDto circleDto = new CircleDto(1, "CIRCLE", 2.5, 0, "creator",
                LocalDateTime.now(), LocalDateTime.now(), "creator", 19.63, 15.71);

        Mockito.when(modelMapper.map(circle, CircleDto.class)).thenReturn(circleDto);
        ShapeDto result = circleDtoCreator.getShapeDto(circle);

        assertEquals(circleDto, result);
    }
}