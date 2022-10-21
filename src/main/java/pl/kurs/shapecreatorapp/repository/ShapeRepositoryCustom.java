package pl.kurs.shapecreatorapp.repository;

import pl.kurs.shapecreatorapp.model.Shape;

import java.time.LocalDateTime;
import java.util.List;

public interface ShapeRepositoryCustom {

    List<Shape> findShapeByParams(String type, Double areaFrom, Double areaTo, Double perimeterFrom, Double perimeterTo,
                                  LocalDateTime createdFrom, LocalDateTime createdTo, String createdBy, Double widthFrom,
                                  Double widthTo, Double heightFrom, Double heightTo, Double radiusFrom, Double radiusTo);

}
