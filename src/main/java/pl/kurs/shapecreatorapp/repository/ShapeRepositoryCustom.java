package pl.kurs.shapecreatorapp.repository;

import pl.kurs.shapecreatorapp.model.SearchShapeQuery;
import pl.kurs.shapecreatorapp.model.Shape;

import java.util.List;

public interface ShapeRepositoryCustom {
    List<Shape> findShapeByParams(SearchShapeQuery searchShapeQuery);
}
