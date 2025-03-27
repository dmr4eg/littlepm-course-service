package pm.little.api.models.ids;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * Composite ID for ProjectDaysMapper
 */
@Embeddable
@Schema(name = "ProjectDaysMapperId", description = "Composite ID for ProjectDaysMapper")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class ProjectDaysMapperId {

  private UUID projectBlueprint;

  private UUID day;

  public ProjectDaysMapperId() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProjectDaysMapperId(UUID projectBlueprint, UUID day) {
    this.projectBlueprint = projectBlueprint;
    this.day = day;
  }

  public ProjectDaysMapperId projectBlueprint(UUID projectBlueprint) {
    this.projectBlueprint = projectBlueprint;
    return this;
  }

  /**
   * UUID for a Project Blueprint
   * @return projectBlueprint
   */
  @NotNull
  @Valid
  @Schema(name = "project_blueprint", description = "UUID for a Project Blueprint", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("project_blueprint")
  public UUID getProjectBlueprint() {
    return projectBlueprint;
  }

  public void setProjectBlueprint(UUID projectBlueprint) {
    this.projectBlueprint = projectBlueprint;
  }

  public ProjectDaysMapperId day(UUID day) {
    this.day = day;
    return this;
  }

  /**
   * UUID for a Day Blueprint
   * @return day
   */
  @NotNull @Valid 
  @Schema(name = "day", description = "UUID for a Day Blueprint", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("day")
  public UUID getDay() {
    return day;
  }

  public void setDay(UUID day) {
    this.day = day;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectDaysMapperId projectDaysMapperId = (ProjectDaysMapperId) o;
    return Objects.equals(this.projectBlueprint, projectDaysMapperId.projectBlueprint) &&
        Objects.equals(this.day, projectDaysMapperId.day);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectBlueprint, day);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectDaysMapperId {\n");
    sb.append("    projectBlueprint: ").append(toIndentedString(projectBlueprint)).append("\n");
    sb.append("    day: ").append(toIndentedString(day)).append("\n");
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

