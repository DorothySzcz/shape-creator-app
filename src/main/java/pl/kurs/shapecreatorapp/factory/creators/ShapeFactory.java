package pl.kurs.shapecreatorapp.factory.creators;

import org.springframework.stereotype.Service;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.command.CreateShapeCommand;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShapeFactory {
    private final Map<String, ShapeCreator> creators;

    public ShapeFactory(Set<ShapeCreator> creators) {
        this.creators = creators.stream().collect(Collectors.toMap(ShapeCreator::getType, Function.identity()));
    }

    public Shape create(CreateShapeCommand command) {
        return creators.get(command.getType()).create(command.getParameters());
    }
}
