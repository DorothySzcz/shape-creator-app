package pl.kurs.shapecreatorapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.kurs.shapecreatorapp.factory.creators.ShapeFactory;
import pl.kurs.shapecreatorapp.model.Circle;
import pl.kurs.shapecreatorapp.model.Rectangle;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.Square;
import pl.kurs.shapecreatorapp.model.command.CreateShapeCommand;
import pl.kurs.shapecreatorapp.repository.ShapeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShapeServiceImplTest {

    @Mock private ShapeRepository shapeRepository;
    @Mock private ShapeFactory shapeFactory;
    private ShapeService shapeService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        shapeService = new ShapeServiceImpl(shapeRepository, shapeFactory);
    }

    @Test
    void shouldCreateNewShape() {

        CreateShapeCommand createShapeCommand = new CreateShapeCommand("SQUARE", List.of(2.5));

        Square square = new Square("SQUARE", 2.5);
        square.setId(0);
        square.setVersion(0);
        square.calculateArea();
        square.calculatePerimeter();

        Mockito.when(shapeFactory.create(createShapeCommand)).thenReturn(square);

        Square expectedShape = new Square("SQUARE", 2.5);
        expectedShape.setId(0);
        expectedShape.setVersion(0);
        expectedShape.setCreatedBy("creator");
        expectedShape.setCreatedAt(LocalDateTime.now());
        expectedShape.setLastModifiedAt(LocalDateTime.now());
        expectedShape.setLastModifiedBy("creator");
        expectedShape.calculateArea();
        expectedShape.calculatePerimeter();
        Mockito.when(shapeRepository.saveAndFlush(square)).thenReturn(expectedShape);

        Shape result = shapeService.createShape(createShapeCommand);

        assertEquals(expectedShape, result);
        assertEquals(expectedShape.calculateArea(), result.calculateArea());
    }


    @Test
    void shouldReturnAllShapes() {

        Square shape1 = new Square("SQUARE", 2.5);
        shape1.setId(0);
        shape1.setVersion(0);
        shape1.setCreatedBy("creator");
        shape1.setCreatedAt(LocalDateTime.now());
        shape1.setLastModifiedAt(LocalDateTime.now());
        shape1.setLastModifiedBy("creator");
        shape1.calculateArea();
        shape1.calculatePerimeter();

        Circle shape2 = new Circle("CIRCLE", 4.5);
        shape2.setId(1);
        shape2.setVersion(0);
        shape2.setCreatedBy("creator");
        shape2.setCreatedAt(LocalDateTime.now());
        shape2.setLastModifiedAt(LocalDateTime.now());
        shape2.setLastModifiedBy("creator");
        shape2.calculateArea();
        shape2.calculatePerimeter();

        Rectangle shape3 = new Rectangle("RECTANGLE", 4.5, 3.4);
        shape3.setId(2);
        shape3.setVersion(0);
        shape3.setCreatedBy("creator");
        shape3.setCreatedAt(LocalDateTime.now());
        shape3.setLastModifiedAt(LocalDateTime.now());
        shape3.setLastModifiedBy("creator");
        shape3.calculateArea();
        shape3.calculatePerimeter();

        List<Shape> shapeList = List.of(shape1, shape2, shape3);

        Mockito.when(shapeRepository.findAll()).thenReturn(shapeList);

        List<Shape> result = shapeService.findAll();

        assertEquals(shapeList, result);
        assertEquals(shapeList.get(0), result.get(0));
        assertEquals(shapeList.get(1), result.get(1));
        assertEquals(shapeList.get(2), result.get(2));
    }

    @Test
    void shouldReturnSquareById() {

        Shape shape1 = new Square("SQUARE", 2.5);
        shape1.setId(0);
        shape1.setVersion(0);
        shape1.setCreatedBy("creator");
        shape1.setCreatedAt(LocalDateTime.now());
        shape1.setLastModifiedAt(LocalDateTime.now());
        shape1.setLastModifiedBy("creator");
        shape1.calculateArea();
        shape1.calculatePerimeter();

        Optional expectedShape = Optional.of(shape1);
        Mockito.when(shapeRepository.findById(0)).thenReturn(expectedShape);

        Optional<Shape> result = shapeService.findById(0);
        assertEquals(expectedShape, result);
    }

}