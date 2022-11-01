package pl.kurs.shapecreatorapp.mappings;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.shapecreatorapp.model.Circle;
import pl.kurs.shapecreatorapp.model.dto.CircleDto;

@Service
public class CircleToCircleFullDtoConverter implements Converter<Circle, CircleDto> {
    @Override
    public CircleDto convert(MappingContext<Circle, CircleDto> mappingContext) {
        Circle source = mappingContext.getSource();
        CircleDto dto = CircleDto.builder()
                .id(source.getId())
                .type(source.getType())
                .radius(source.getRadius())
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
