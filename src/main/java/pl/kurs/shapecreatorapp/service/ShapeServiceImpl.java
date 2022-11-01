package pl.kurs.shapecreatorapp.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.shapecreatorapp.exception.NoPermissionException;
import pl.kurs.shapecreatorapp.exception.ShapeNotFoundException;
import pl.kurs.shapecreatorapp.factory.creators.ShapeFactory;
import pl.kurs.shapecreatorapp.model.SearchShapeQuery;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.command.CreateShapeCommand;
import pl.kurs.shapecreatorapp.model.command.UpdateShapeCommand;
import pl.kurs.shapecreatorapp.repository.ShapeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShapeServiceImpl implements ShapeService {
    private final ShapeRepository shapeRepository;
    private final ShapeFactory shapeFactory;

    public ShapeServiceImpl(ShapeRepository shapeRepository, ShapeFactory shapeFactory) {
        this.shapeRepository = shapeRepository;
        this.shapeFactory = shapeFactory;
    }

    @Transactional(readOnly = true)
    public List<Shape> findAll() {
        return shapeRepository.findAll();
    }

    @Transactional
    public Shape createShape(CreateShapeCommand command) {
        return shapeRepository.saveAndFlush(shapeFactory.create(command));
    }

    @Transactional(readOnly = true)
    public Optional<Shape> findById(int id) {
        return shapeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Shape> getAllShapesByParams(SearchShapeQuery searchShapeQuery) {
        return shapeRepository.findShapeByParams(searchShapeQuery);
    }

    @Override
    public Shape editShape(int id, UpdateShapeCommand dataToEdit) throws NoPermissionException {
        Shape toEdit = shapeRepository.findById(id)
                .orElseThrow(() -> new ShapeNotFoundException("SHAPE_NOT_FOUND"));
        checkAccess(toEdit);
        return shapeRepository.saveAndFlush(toEdit);
    }

    private void checkAccess(Shape shape) throws NoPermissionException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null && auth.getAuthorities()
                .stream()
                .anyMatch(a -> !a.getAuthority().equals("ROLE_ADMIN") && !a.getAuthority().equals(shape.getCreatedBy()))) {
            throw new NoPermissionException("NO_PERMISSION_TO_RESOURCES");
        }
    }
}
