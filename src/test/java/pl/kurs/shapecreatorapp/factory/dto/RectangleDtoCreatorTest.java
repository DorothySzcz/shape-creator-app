package pl.kurs.shapecreatorapp.factory.dto;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import pl.kurs.shapecreatorapp.model.Rectangle;
import pl.kurs.shapecreatorapp.model.dto.RectangleDto;
import pl.kurs.shapecreatorapp.model.dto.ShapeDto;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleDtoCreatorTest {

    private RectangleDtoCreator rectangleDtoCreator;

    @Test
    void shouldReturnRectangleDtoWhenShapeHasRectangleType() {

        ModelMapper modelMapper = Mockito.mock(ModelMapper.class);
        rectangleDtoCreator = new RectangleDtoCreator(modelMapper);

        Rectangle rectangle = new Rectangle("RECTANGLE", 2.0, 4.0);
        rectangle.setId(1);
        rectangle.setVersion(0);
        rectangle.setCreatedBy("creator");
        rectangle.setCreatedAt(LocalDateTime.now());
        rectangle.setLastModifiedAt(LocalDateTime.now());
        rectangle.setLastModifiedBy("creator");

        RectangleDto rectangleDto = new RectangleDto(1, "RECTANGLE", 2.0, 4.0, 0, "creator",
                LocalDateTime.now(), LocalDateTime.now(), "creator", 19.63, 12.00);

        Mockito.when(modelMapper.map(rectangle, RectangleDto.class)).thenReturn(rectangleDto);
        ShapeDto result = rectangleDtoCreator.getShapeDto(rectangle);

        assertEquals(rectangleDto, result);
    }
}