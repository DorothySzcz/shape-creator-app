package pl.kurs.shapecreatorapp.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.shapecreatorapp.exception.ShapeNotFoundException;
import pl.kurs.shapecreatorapp.factory.dto.ShapeDtoFactory;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.command.CreateShapeCommand;
import pl.kurs.shapecreatorapp.model.dto.ShapeDto;
import pl.kurs.shapecreatorapp.service.ShapeServiceImpl;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/shapes")
@RequiredArgsConstructor
public class ShapeController {
    private final ShapeServiceImpl shapeServiceImpl;
    private final ModelMapper modelMapper;
    private final ShapeDtoFactory shapeDtoFactory;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid CreateShapeCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(shapeServiceImpl.createShape(command)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShapeDto> getSingleShape(@PathVariable(name = "id") int id) {
        return shapeServiceImpl.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ShapeNotFoundException("Shape not found!"));
    }

    @GetMapping("/parameters")
    public ResponseEntity<List<ShapeDto>> getShapesByParams(@RequestParam(value = "type", required = false) String type,
                                                            @RequestParam(value = "areaFrom", required = false) Double areaFrom,
                                                            @RequestParam(value = "areaTo", required = false) Double areaTo,
                                                            @RequestParam(value = "perimeterFrom", required = false) Double perimeterFrom,
                                                            @RequestParam(value = "perimeterTo", required = false) Double perimeterTo,
                                                            @RequestParam(value = "createdFrom", required = false) LocalDateTime createdFrom,
                                                            @RequestParam(value = "createdTo", required = false) LocalDateTime createdTo,
                                                            @RequestParam(value = "createdBy", required = false) String createdBy,
                                                            @RequestParam(value = "widthFrom", required = false) Double widthFrom,
                                                            @RequestParam(value = "widthTo", required = false) Double widthTo,
                                                            @RequestParam(value = "heightFrom", required = false) Double heightFrom,
                                                            @RequestParam(value = "heightTo", required = false) Double heightTo,
                                                            @RequestParam(value = "radiusFrom", required = false) Double radiusFrom,
                                                            @RequestParam(value = "radiusTo", required = false) Double radiusTo) {
        return ResponseEntity.ok(shapeServiceImpl.getAllShapesByParams(type, areaFrom, areaTo, perimeterFrom, perimeterTo, createdFrom, createdTo,
                createdBy, widthFrom, widthTo, heightFrom, heightTo, radiusFrom, radiusTo)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList()));
    }

    private ShapeDto toDto(Shape shape) {
        return modelMapper.map(shape, shapeDtoFactory.findShapeConverterClass(shape).getClass());
    }

}
