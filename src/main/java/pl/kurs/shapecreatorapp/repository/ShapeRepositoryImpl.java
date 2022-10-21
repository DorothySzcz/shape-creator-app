package pl.kurs.shapecreatorapp.repository;

import org.springframework.stereotype.Repository;
import pl.kurs.shapecreatorapp.model.Shape;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShapeRepositoryImpl implements ShapeRepositoryCustom {

    private final EntityManager entityManager;

    public ShapeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Shape> findShapeByParams(String type, Double areaFrom, Double areaTo, Double perimeterFrom, Double perimeterTo,
                                         LocalDateTime createdFrom, LocalDateTime createdTo, String createdBy, Double widthFrom,
                                         Double widthTo, Double heightFrom, Double heightTo, Double radiusFrom, Double radiusTo) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shape> cq = cb.createQuery(Shape.class);

        Root<Shape> shape = cq.from(Shape.class);
        List<Predicate> predicates = new ArrayList<>();

        if (type != null) {
            predicates.add(cb.equal(shape.get("type"), type));
        }
        if (areaFrom != null) {
            predicates.add(cb.greaterThanOrEqualTo(shape.get("area"), areaFrom));
        }
        if (areaTo != null) {
            predicates.add(cb.lessThanOrEqualTo(shape.get("area"), areaTo));
        }
        if (perimeterFrom != null) {
            predicates.add(cb.greaterThanOrEqualTo(shape.get("perimeter"), perimeterFrom));
        }
        if (perimeterTo != null) {
            predicates.add(cb.lessThanOrEqualTo(shape.get("perimeter"), perimeterTo));
        }
        if (createdFrom != null) {
            predicates.add(cb.greaterThanOrEqualTo(shape.get("createdAt"), createdFrom));
        }
        if (createdTo != null) {
            predicates.add(cb.lessThanOrEqualTo(shape.get("createdAt"), createdTo));
        }
        if (createdBy != null) {
            predicates.add(cb.equal(shape.get("createdBy"), createdBy));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }

}