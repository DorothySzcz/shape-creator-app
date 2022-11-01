package pl.kurs.shapecreatorapp.factory.creators;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import pl.kurs.shapecreatorapp.exception.BadQuantityOfParametersException;
import pl.kurs.shapecreatorapp.model.Circle;
import pl.kurs.shapecreatorapp.model.Shape;

import java.util.List;

import static org.junit.Assert.assertThrows;

public class CircleCreatorTest {

    private CircleCreator circleCreator;

    @Before
    public void init() {
        circleCreator = new CircleCreator();
    }

    @Test
    public void shouldCreateCircleWhenShapeTypeIsCircleAndHasOneParameter() {
        List<Double> parameters = List.of(3.5);

        Shape result = circleCreator.create(parameters);

        Shape expectedShape = new Circle("CIRCLE", 3.5);
        Assertions.assertThat(result).isEqualTo(expectedShape);
    }

    @Test
    public void shouldThrowBadQuantityOfParametersExceptionWhenIsWrongQuantityOfParameters() {
        List<Double> parameters = List.of(3.5, 2.0);

        Throwable e = assertThrows(BadQuantityOfParametersException.class, () -> circleCreator.create(parameters));

        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(BadQuantityOfParametersException.class);
        sa.assertThat(e).hasMessage("BAD_QUANTITY_OF_PARAMETERS");
        sa.assertAll();
    }

}