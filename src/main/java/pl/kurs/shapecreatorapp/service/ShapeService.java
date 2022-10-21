package pl.kurs.shapecreatorapp.service;

import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.command.CreateShapeCommand;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShapeService {

    List<Shape> findAll();

    Optional<Shape> findById(int id);

    Shape createShape(CreateShapeCommand command);

    List<Shape> getAllShapesByParams(String type, Double areaFrom, Double areaTo, Double perimeterFrom, Double perimeterTo,
                                     LocalDateTime createdFrom, LocalDateTime createdTo, String createdBy, Double widthFrom,
                                     Double widthTo, Double heightFrom, Double heightTo, Double radiusFrom, Double radiusTo);
}
