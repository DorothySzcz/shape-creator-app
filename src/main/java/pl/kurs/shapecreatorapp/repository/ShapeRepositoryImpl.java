package pl.kurs.shapecreatorapp.repository;

import org.springframework.stereotype.Repository;
import pl.kurs.shapecreatorapp.model.SearchShapeQuery;
import pl.kurs.shapecreatorapp.model.Shape;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShapeRepositoryImpl implements ShapeRepositoryCustom {

    private final EntityManager entityManager;

    public ShapeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Shape> findShapeByParams(SearchShapeQuery searchShapeQuery) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shape> cq = cb.createQuery(Shape.class);

        Root<Shape> shape = cq.from(Shape.class);

        List<Predicate> predicates = new ArrayList<>();

        if (searchShapeQuery.getType() != null)
            predicates.add(cb.equal(shape.get("type"), searchShapeQuery.getType()));

        if (searchShapeQuery.getAreaFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(shape.get("area"), searchShapeQuery.getAreaFrom()));
        }
        if (searchShapeQuery.getAreaTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(shape.get("area"), searchShapeQuery.getAreaTo()));
        }
        if (searchShapeQuery.getPerimeterFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(shape.get("perimeter"), searchShapeQuery.getPerimeterFrom()));
        }
        if (searchShapeQuery.getPerimeterTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(shape.get("perimeter"), searchShapeQuery.getPerimeterTo()));
        }
        if (searchShapeQuery.getCreatedFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(shape.get("createdAt"), searchShapeQuery.getCreatedFrom()));
        }
        if (searchShapeQuery.getCreatedTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(shape.get("createdAt"), searchShapeQuery.getCreatedTo()));
        }
        if (searchShapeQuery.getCreatedBy() != null) {
            predicates.add(cb.equal(shape.get("createdBy"), searchShapeQuery.getCreatedBy()));
        }
        if (searchShapeQuery.getWidthFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(shape.get("width"), searchShapeQuery.getWidthFrom()));
        }
        if (searchShapeQuery.getWidthTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(shape.get("width"), searchShapeQuery.getWidthTo()));
        }
        if (searchShapeQuery.getHeightFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(shape.get("height"), searchShapeQuery.getHeightFrom()));
        }
        if (searchShapeQuery.getHeightTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(shape.get("height"), searchShapeQuery.getHeightTo()));
        }
        if (searchShapeQuery.getRadiusFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(shape.get("radius"), searchShapeQuery.getRadiusFrom()));
        }
        if (searchShapeQuery.getRadiusTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(shape.get("radius"), searchShapeQuery.getRadiusTo()));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }

}