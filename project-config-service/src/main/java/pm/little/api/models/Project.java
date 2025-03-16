package pm.little.api.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;

/**
 * Project
 */

public class Project {

  private UUID projectUUID;

  private String name;

  private @Nullable String description;

  private UUID ownerUUID;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime updatedAt;

  public Project() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Project(UUID projectUUID, String name, UUID ownerUUID) {
    this.projectUUID = projectUUID;
    this.name = name;
    this.ownerUUID = ownerUUID;
  }

  public Project projectUUID(UUID projectUUID) {
    this.projectUUID = projectUUID;
    return this;
  }

  /**
   * Get projectUUID
   * @return projectUUID
   */
  @NotNull
  @Valid
  @Schema(name = "projectUUID", example = "550e8400-e29b-41d4-a716-446655440000", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("projectUUID")
  public UUID getProjectUUID() {
    return projectUUID;
  }

  public void setProjectUUID(UUID projectUUID) {
    this.projectUUID = projectUUID;
  }

  public Project name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull 
  @Schema(name = "name", example = "Q4 Marketing Campaign", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Project description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  
  @Schema(name = "description", example = "Year-end marketing push", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Project ownerUUID(UUID ownerUUID) {
    this.ownerUUID = ownerUUID;
    return this;
  }

  /**
   * Get ownerUUID
   * @return ownerUUID
   */
  @NotNull @Valid 
  @Schema(name = "ownerUUID", example = "550e8400-e29b-41d4-a716-446655440000", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ownerUUID")
  public UUID getOwnerUUID() {
    return ownerUUID;
  }

  public void setOwnerUUID(UUID ownerUUID) {
    this.ownerUUID = ownerUUID;
  }

  public Project createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
   */
  @Valid 
  @Schema(name = "createdAt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createdAt")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Project updatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  /**
   * Get updatedAt
   * @return updatedAt
   */
  @Valid 
  @Schema(name = "updatedAt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("updatedAt")
  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Project project = (Project) o;
    return Objects.equals(this.projectUUID, project.projectUUID) &&
        Objects.equals(this.name, project.name) &&
        Objects.equals(this.description, project.description) &&
        Objects.equals(this.ownerUUID, project.ownerUUID) &&
        Objects.equals(this.createdAt, project.createdAt) &&
        Objects.equals(this.updatedAt, project.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectUUID, name, description, ownerUUID, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Project {\n");
    sb.append("    projectUUID: ").append(toIndentedString(projectUUID)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    ownerUUID: ").append(toIndentedString(ownerUUID)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
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

