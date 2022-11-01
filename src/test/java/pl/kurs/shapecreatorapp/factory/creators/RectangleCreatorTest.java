package pl.kurs.shapecreatorapp.factory.creators;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import pl.kurs.shapecreatorapp.exception.BadQuantityOfParametersException;
import pl.kurs.shapecreatorapp.model.Rectangle;
import pl.kurs.shapecreatorapp.model.Shape;

import java.util.List;

import static org.junit.Assert.assertThrows;

public class RectangleCreatorTest {

    private RectangleCreator rectangleCreator;

    @Before
    public void init() {
        rectangleCreator = new RectangleCreator();
    }

    @Test
    public void shouldCreateRectangleWhenShapeTypeIsRectangleAndHasTwoParameters() {
        List<Double> parameters = List.of(3.5, 4.5);

        Shape result = rectangleCreator.create(parameters);

        Shape expectedShape = new Rectangle("RECTANGLE", 3.5, 4.5);
        Assertions.assertThat(result).isEqualTo(expectedShape);
    }

    @Test
    public void shouldThrowBadQuantityOfParametersExceptionWhenIsWrongQuantityOfParameters() {
        List<Double> parameters = List.of(3.5);

        Throwable e = assertThrows(BadQuantityOfParametersException.class, () -> rectangleCreator.create(parameters));

        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(BadQuantityOfParametersException.class);
        sa.assertThat(e).hasMessage("BAD_QUANTITY_OF_PARAMETERS");
        sa.assertAll();
    }
}