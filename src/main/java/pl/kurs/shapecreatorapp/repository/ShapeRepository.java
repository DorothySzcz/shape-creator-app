package pl.kurs.shapecreatorapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import pl.kurs.shapecreatorapp.model.SearchShapeQuery;
import pl.kurs.shapecreatorapp.model.Shape;

import java.util.List;

public interface ShapeRepository extends JpaRepository<Shape, Integer>, JpaSpecificationExecutor<Shape>, ShapeRepositoryCustom {

    List<Shape> findShapeByParams(SearchShapeQuery searchShapeQuery);

}
