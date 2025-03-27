package pm.little.api.models.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import pm.little.api.models.ProjectBlueprint;
import pm.little.api.models.ProjectInstance;


import io.swagger.v3.oas.annotations.media.Schema;



/**
 * ProjectDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class ProjectDTO {

  private ProjectBlueprint blueprint;

  private ProjectInstance instance;

  public ProjectDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProjectDTO(ProjectBlueprint blueprint, ProjectInstance instance) {
    this.blueprint = blueprint;
    this.instance = instance;
  }

  public ProjectDTO blueprint(ProjectBlueprint blueprint) {
    this.blueprint = blueprint;
    return this;
  }

  /**
   * Get blueprint
   * @return blueprint
   */
  @NotNull
  @Valid
  @Schema(name = "blueprint", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("blueprint")
  public ProjectBlueprint getBlueprint() {
    return blueprint;
  }

  public void setBlueprint(ProjectBlueprint blueprint) {
    this.blueprint = blueprint;
  }

  public ProjectDTO instance(ProjectInstance instance) {
    this.instance = instance;
    return this;
  }

  /**
   * Get instance
   * @return instance
   */
  @NotNull @Valid 
  @Schema(name = "instance", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("instance")
  public ProjectInstance getInstance() {
    return instance;
  }

  public void setInstance(ProjectInstance instance) {
    this.instance = instance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectDTO projectDTO = (ProjectDTO) o;
    return Objects.equals(this.blueprint, projectDTO.blueprint) &&
        Objects.equals(this.instance, projectDTO.instance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blueprint, instance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectDTO {\n");
    sb.append("    blueprint: ").append(toIndentedString(blueprint)).append("\n");
    sb.append("    instance: ").append(toIndentedString(instance)).append("\n");
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

