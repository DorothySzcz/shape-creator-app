package pl.kurs.shapecreatorapp.mappings;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.shapecreatorapp.model.Square;
import pl.kurs.shapecreatorapp.model.dto.SquareDto;

@Service
public class SquareToSquareFullDtoConverter implements Converter<Square, SquareDto> {
    @Override
    public SquareDto convert(MappingContext<Square, SquareDto> mappingContext) {
        Square source = mappingContext.getSource();
        SquareDto dto = SquareDto.builder()
                .id(source.getId())
                .type(source.getType())
                .width(source.getWidth())
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
