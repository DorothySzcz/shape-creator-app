package pl.kurs.shapecreatorapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.Objects;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EntityListeners({AuditingEntityListener.class})
@MappedSuperclass
@Table(name = "auditable_entity")
public abstract class AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Version
    private int version;
    @CreatedBy
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedAt;
    @LastModifiedBy
    private String lastModifiedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
        AuditableEntity that = (AuditableEntity) o;
        return id == that.id && version == that.version && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdAt, that.createdAt) && Objects.equals(lastModifiedAt, that.lastModifiedAt) && Objects.equals(lastModifiedBy, that.lastModifiedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, createdBy, createdAt, lastModifiedAt, lastModifiedBy);
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", version=" + version +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", lastModifiedAt=" + lastModifiedAt +
                ", lastModifiedBy='" + lastModifiedBy + '\'';
    }
}
