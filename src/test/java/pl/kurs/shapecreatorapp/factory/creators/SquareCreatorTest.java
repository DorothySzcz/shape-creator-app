package pl.kurs.shapecreatorapp.factory.creators;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import pl.kurs.shapecreatorapp.exception.BadQuantityOfParametersException;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.Square;

import java.util.List;

import static org.junit.Assert.assertThrows;

public class SquareCreatorTest {

    private SquareCreator squareCreator;

    @Before
    public void init() {
        squareCreator = new SquareCreator();
    }


    @Test
    public void shouldCreateSquareWhenShapeTypeIsSquareAndHasOneParameter() {
        List<Double> parameters = List.of(3.5);

        Shape result = squareCreator.create(parameters);

        Shape expectedShape = new Square("SQUARE", 3.5);
        Assertions.assertThat(result).isEqualTo(expectedShape);
    }

    @Test
    public void shouldThrowBadQuantityOfParametersExceptionWhenIsWrongQuantityOfParameters() {
        List<Double> parameters = List.of(3.5, 7.2);

        Throwable e = assertThrows(BadQuantityOfParametersException.class, () -> squareCreator.create(parameters));

        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(BadQuantityOfParametersException.class);
        sa.assertThat(e).hasMessage("BAD_QUANTITY_OF_PARAMETERS");
        sa.assertAll();
    }
}