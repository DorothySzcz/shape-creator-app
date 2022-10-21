package pl.kurs.shapecreatorapp.factory.creators;

import pl.kurs.shapecreatorapp.model.Shape;

import java.util.List;

public interface ShapeCreator {
    String getType();

    Shape create(List<Double> parameters);
}
