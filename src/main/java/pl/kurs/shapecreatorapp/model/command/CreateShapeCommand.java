package pl.kurs.shapecreatorapp.model.command;

import lombok.Data;
import pl.kurs.shapecreatorapp.validation.annotation.NotNegative;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class CreateShapeCommand {
    @NotEmpty(message = "TYPE_IS_EMPTY")
    @Pattern(regexp = "^[A-Z ]+$", message = "TYPE_IS_NOT_VALID")
    private String type;
    @NotEmpty(message = "PARAMETERS_NOT_EMPTY")
    @NotNull(message = "PARAMETERS_NOT_NULL")
    @NotNegative
    private List<@Valid Double> parameters;

    public CreateShapeCommand(@NotEmpty(message = "TYPE_IS_EMPTY") @Pattern(regexp = "^[A-Z ]+$", message = "TYPE_IS_NOT_VALID") String type,
                              @NotEmpty(message = "PARAMETERS_NOT_EMPTY") List<@Valid Double> parameters) {
        this.type = type;
        this.parameters = parameters;
    }
}
