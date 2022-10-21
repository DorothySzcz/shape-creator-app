package pl.kurs.shapecreatorapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.shapecreatorapp.factory.creators.ShapeFactory;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.command.CreateShapeCommand;
import pl.kurs.shapecreatorapp.repository.ShapeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShapeServiceImpl implements ShapeService {
    private final ShapeRepository shapeRepository;
    private final ShapeFactory shapeFactory;

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
    public List<Shape> getAllShapesByParams(String type, Double areaFrom, Double areaTo, Double perimeterFrom, Double perimeterTo,
                                            LocalDateTime createdFrom, LocalDateTime createdTo, String createdBy, Double widthFrom,
                                            Double widthTo, Double heightFrom, Double heightTo, Double radiusFrom, Double radiusTo) {
        return shapeRepository.findShapeByParams(type, areaFrom, areaTo, perimeterFrom, perimeterTo, createdFrom, createdTo,
                createdBy, widthFrom, widthTo, heightFrom, heightTo, radiusFrom, radiusTo);
    }

}
