package pl.kurs.shapecreatorapp.mappings;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.shapecreatorapp.model.Rectangle;
import pl.kurs.shapecreatorapp.model.dto.RectangleDto;

@Service
public class RectangleToRectangleFullDtoConverter implements Converter<Rectangle, RectangleDto> {
    @Override
    public RectangleDto convert(MappingContext<Rectangle, RectangleDto> mappingContext) {
        Rectangle source = mappingContext.getSource();
        RectangleDto dto = RectangleDto.builder()
                .id(source.getId())
                .type(source.getType())
                .width(source.getWidth())
                .height(source.getHeight())
                .version(source.getVersion())
                .createdBy(source.getCreatedBy())
                .createdAt(source.getCreatedAt())
                .lastModifiedAt(source.getLastModifiedAt())
                .lastModifiedBy(source.getLastModifiedBy())
                .area(source.calculateArea())
                .perimeter(source.calculatePerimeter())
                .build();
        return dto;
    }
}
