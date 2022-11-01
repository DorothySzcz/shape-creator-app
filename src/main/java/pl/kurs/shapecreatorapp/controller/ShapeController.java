package pl.kurs.shapecreatorapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.shapecreatorapp.exception.NoPermissionException;
import pl.kurs.shapecreatorapp.exception.ShapeNotFoundException;
import pl.kurs.shapecreatorapp.factory.dto.ShapeDtoFactory;
import pl.kurs.shapecreatorapp.model.SearchShapeQuery;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.command.CreateShapeCommand;
import pl.kurs.shapecreatorapp.model.command.UpdateShapeCommand;
import pl.kurs.shapecreatorapp.model.dto.ShapeDto;
import pl.kurs.shapecreatorapp.service.ShapeServiceImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/shapes")
public class ShapeController {
    private final ShapeServiceImpl shapeServiceImpl;
    private final ShapeDtoFactory shapeDtoFactory;

    public ShapeController(ShapeServiceImpl shapeServiceImpl, ShapeDtoFactory shapeDtoFactory) {
        this.shapeServiceImpl = shapeServiceImpl;
        this.shapeDtoFactory = shapeDtoFactory;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CREATOR')")
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

    @GetMapping()
    public ResponseEntity<List<ShapeDto>> getShapesByParams(SearchShapeQuery searchShapeQuery) {
        return ResponseEntity.ok(shapeServiceImpl.getAllShapesByParams(searchShapeQuery)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('ROLE_CREATOR') or hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity editShape(@PathVariable int id, @RequestBody UpdateShapeCommand dataToEdit) throws NoPermissionException {
        Shape edited = shapeServiceImpl.editShape(id, dataToEdit);
        return new ResponseEntity(toDto(edited), HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ROLE_CREATOR') or hasRole('ROLE_ADMIN')")
//    @GetMapping("/{id}/history")
//    public ResponseEntity getShapeHistory(@PathVariable int id) {
//        Shape shape = shapeServiceImpl.findById(id)
//                .orElseThrow(() -> new ShapeNotFoundException("Shape not found!"));
//        return ResponseEntity.ok(shape.getHistory().stream()
//                .map(this::toDto)
//                .collect(Collectors.toList()));
//    }

    private ShapeDto toDto(Shape shape) {
        return shapeDtoFactory.findShapeDto(shape);
    }

}
