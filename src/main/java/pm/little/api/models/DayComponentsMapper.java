package pm.little.api.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Generated;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import pm.little.api.models.ids.DayComponentsMapperId;

import io.swagger.v3.oas.annotations.media.Schema;
import pm.little.api.models.enums.TypeEnum;


/**
 * Mapping of components (tasks, forms, media) within a day
 */
@Entity
@Schema(name = "DayComponentsMapper", description = "Mapping of components (tasks, forms, media) within a day")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class DayComponentsMapper {

  @EmbeddedId
  @NotNull
  private DayComponentsMapperId id;

  /**
   * Gets or Sets type
   */

  @NotNull
  private TypeEnum type;

  @NotNull
  private Integer order;

  public @NotNull Integer getOrder() {
    return order;
  }

  public void setOrder(@NotNull Integer order) {
    this.order = order;
  }

  public DayComponentsMapper() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public DayComponentsMapper(DayComponentsMapperId id, TypeEnum type) {
    this.id = id;
    this.type = type;
  }

  public DayComponentsMapper id(DayComponentsMapperId id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @NotNull @Valid
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public DayComponentsMapperId getId() {
    return id;
  }

  public void setId(DayComponentsMapperId id) {
    this.id = id;
  }

  public DayComponentsMapper type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  @NotNull 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DayComponentsMapper dayComponentsMapper = (DayComponentsMapper) o;
    return Objects.equals(this.id, dayComponentsMapper.id) &&
        Objects.equals(this.type, dayComponentsMapper.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DayComponentsMapper {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

