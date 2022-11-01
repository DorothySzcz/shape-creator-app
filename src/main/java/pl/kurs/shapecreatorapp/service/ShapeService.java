package pl.kurs.shapecreatorapp.service;

import pl.kurs.shapecreatorapp.exception.NoPermissionException;
import pl.kurs.shapecreatorapp.model.SearchShapeQuery;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.command.CreateShapeCommand;
import pl.kurs.shapecreatorapp.model.command.UpdateShapeCommand;

import java.util.List;
import java.util.Optional;

public interface ShapeService {

    List<Shape> findAll();

    Optional<Shape> findById(int id);

    Shape createShape(CreateShapeCommand command);

    List<Shape> getAllShapesByParams(SearchShapeQuery searchShapeQuery);

    Shape editShape(int id, UpdateShapeCommand dataToEdit) throws NoPermissionException;


}
