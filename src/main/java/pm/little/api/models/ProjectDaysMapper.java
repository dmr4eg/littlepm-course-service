package pm.little.api.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;


/**
 * ProjectDaysMapper
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-26T00:36:51.210059+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class ProjectDaysMapper {

  private @Nullable UUID projectBlueprint;

  private @Nullable UUID day;

  private @Nullable Integer order;

  public ProjectDaysMapper projectBlueprint(UUID projectBlueprint) {
    this.projectBlueprint = projectBlueprint;
    return this;
  }

  /**
   * UUID referencing a ProjectBlueprint
   * @return projectBlueprint
   */
  @Valid
  @Schema(name = "project_blueprint", description = "UUID referencing a ProjectBlueprint", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("project_blueprint")
  public UUID getProjectBlueprint() {
    return projectBlueprint;
  }

  public void setProjectBlueprint(UUID projectBlueprint) {
    this.projectBlueprint = projectBlueprint;
  }

  public ProjectDaysMapper day(UUID day) {
    this.day = day;
    return this;
  }

  /**
   * UUID referencing a DayBlueprint
   * @return day
   */
  @Valid 
  @Schema(name = "day", description = "UUID referencing a DayBlueprint", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("day")
  public UUID getDay() {
    return day;
  }

  public void setDay(UUID day) {
    this.day = day;
  }

  public ProjectDaysMapper order(Integer order) {
    this.order = order;
    return this;
  }

  /**
   * The order of the day in the project
   * @return order
   */
  
  @Schema(name = "order", description = "The order of the day in the project", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    return Objects.equals(this.projectBlueprint, projectDaysMapper.projectBlueprint) &&
        Objects.equals(this.day, projectDaysMapper.day) &&
        Objects.equals(this.order, projectDaysMapper.order);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectBlueprint, day, order);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectDaysMapper {\n");
    sb.append("    projectBlueprint: ").append(toIndentedString(projectBlueprint)).append("\n");
    sb.append("    day: ").append(toIndentedString(day)).append("\n");
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

