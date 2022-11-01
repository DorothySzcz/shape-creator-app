package pl.kurs.shapecreatorapp.model.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pl.kurs.shapecreatorapp.validation.annotation.NotNegative;

import javax.persistence.EntityListeners;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
public class UpdateShapeCommand {

    @NotEmpty(message = "PARAMETERS_NOT_EMPTY")
    @NotNull(message = "PARAMETERS_NOT_NULL")
    @NotNegative
    private Double width;
    @NotEmpty(message = "PARAMETERS_NOT_EMPTY")
    @NotNull(message = "PARAMETERS_NOT_NULL")
    @NotNegative
    private Double height;
    @NotEmpty(message = "PARAMETERS_NOT_EMPTY")
    @NotNull(message = "PARAMETERS_NOT_NULL")
    @NotNegative
    private Double radius;
    @Version
    private int version;
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedAt;
    @LastModifiedBy
    private String lastModifiedBy;

    public UpdateShapeCommand(Double width, Double height, Double radius, int version, LocalDateTime lastModifiedAt, String lastModifiedBy) {
        this.width = width;
        this.height = height;
        this.radius = radius;
        this.version = version;
        this.lastModifiedAt = lastModifiedAt;
        this.lastModifiedBy = lastModifiedBy;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateShapeCommand that = (UpdateShapeCommand) o;
        return version == that.version && Objects.equals(width, that.width) && Objects.equals(height, that.height) && Objects.equals(radius, that.radius) && Objects.equals(lastModifiedAt, that.lastModifiedAt) && Objects.equals(lastModifiedBy, that.lastModifiedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, radius, version, lastModifiedAt, lastModifiedBy);
    }
}
