package pm.little.api.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Generated;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import pm.little.api.models.ids.ProjectDaysMapperId;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * Entity for mapping days within a project
 */
@Entity
@Schema(name = "ProjectDaysMapper", description = "Entity for mapping days within a project")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class ProjectDaysMapper {
  @EmbeddedId
  @NotNull
  private ProjectDaysMapperId id;

  private Integer order;

  public ProjectDaysMapper() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProjectDaysMapper(ProjectDaysMapperId id, Integer order) {
    this.id = id;
    this.order = order;
  }

  public ProjectDaysMapper id(ProjectDaysMapperId id) {
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
  public ProjectDaysMapperId getId() {
    return id;
  }

  public void setId(ProjectDaysMapperId id) {
    this.id = id;
  }

  public ProjectDaysMapper order(Integer order) {
    this.order = order;
    return this;
  }

  /**
   * Order or position within a list
   * @return order
   */
  @NotNull 
  @Schema(name = "order", description = "Order or position within a list", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("order")
  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectDaysMapper projectDaysMapper = (ProjectDaysMapper) o;
    return Objects.equals(this.id, projectDaysMapper.id) &&
        Objects.equals(this.order, projectDaysMapper.order);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, order);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectDaysMapper {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    order: ").append(toIndentedString(order)).append("\n");
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

