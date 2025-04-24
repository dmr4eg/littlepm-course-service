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

  private UUID projectBlueprintUuid;

  private UUID dayBlueprintUuid;

  public ProjectDaysMapperId() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProjectDaysMapperId(UUID projectBlueprintUuid, UUID dayBlueprintUuid) {
    this.projectBlueprintUuid = projectBlueprintUuid;
    this.dayBlueprintUuid = dayBlueprintUuid;
  }

  public ProjectDaysMapperId projectBlueprintUuid(UUID projectBlueprintUuid) {
    this.projectBlueprintUuid = projectBlueprintUuid;
    return this;
  }

  /**
   * UUID for a Project Blueprint
   * @return projectBlueprintUuid
   */
  @NotNull @Valid 
  @Schema(name = "project_blueprint_uuid", description = "UUID for a Project Blueprint", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("project_blueprint_uuid")
  public UUID getProjectBlueprintUuid() {
    return projectBlueprintUuid;
  }

  public void setProjectBlueprintUuid(UUID projectBlueprintUuid) {
    this.projectBlueprintUuid = projectBlueprintUuid;
  }

  public ProjectDaysMapperId dayBlueprintUuid(UUID dayBlueprintUuid) {
    this.dayBlueprintUuid = dayBlueprintUuid;
    return this;
  }

  /**
   * UUID for a Day Blueprint
   * @return dayBlueprintUuid
   */
  @NotNull @Valid 
  @Schema(name = "day_blueprint_uuid", description = "UUID for a Day Blueprint", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("day_blueprint_uuid")
  public UUID getDayBlueprintUuid() {
    return dayBlueprintUuid;
  }

  public void setDayBlueprintUuid(UUID dayBlueprintUuid) {
    this.dayBlueprintUuid = dayBlueprintUuid;
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
    return Objects.equals(this.projectBlueprintUuid, projectDaysMapperId.projectBlueprintUuid) &&
        Objects.equals(this.dayBlueprintUuid, projectDaysMapperId.dayBlueprintUuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectBlueprintUuid, dayBlueprintUuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectDaysMapperId {\n");
    sb.append("    projectBlueprintUuid: ").append(toIndentedString(projectBlueprintUuid)).append("\n");
    sb.append("    dayBlueprintUuid: ").append(toIndentedString(dayBlueprintUuid)).append("\n");
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

