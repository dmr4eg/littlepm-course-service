package pm.little.api.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Project
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-22T16:32:52.983981+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class Project {

  private UUID projectUUID;

  private String name;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    IN_PROGRESS("in_progress"),
    
    COMPLETED("completed");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private @Nullable StatusEnum status;

  private @Nullable String description;

  private UUID userUUID;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime updatedAt;

  private @Nullable URI projectImage;

  public Project() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Project(UUID projectUUID, String name, UUID userUUID) {
    this.projectUUID = projectUUID;
    this.name = name;
    this.userUUID = userUUID;
  }

  public Project projectUUID(UUID projectUUID) {
    this.projectUUID = projectUUID;
    return this;
  }

  /**
   * Get projectUUID
   * @return projectUUID
   */
  @NotNull @Valid 
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

  public Project status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   */
  
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
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

  public Project userUUID(UUID userUUID) {
    this.userUUID = userUUID;
    return this;
  }

  /**
   * Get userUUID
   * @return userUUID
   */
  @NotNull @Valid 
  @Schema(name = "userUUID", example = "550e8400-e29b-41d4-a716-446655440000", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("userUUID")
  public UUID getUserUUID() {
    return userUUID;
  }

  public void setUserUUID(UUID userUUID) {
    this.userUUID = userUUID;
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
  @Schema(name = "createdAt", example = "2021-10-01T12:00Z", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
  @Schema(name = "updatedAt", example = "2021-10-01T12:00Z", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("updatedAt")
  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Project projectImage(URI projectImage) {
    this.projectImage = projectImage;
    return this;
  }

  /**
   * Get projectImage
   * @return projectImage
   */
  @Valid 
  @Schema(name = "projectImage", example = "https://example.com/image.jpg", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("projectImage")
  public URI getProjectImage() {
    return projectImage;
  }

  public void setProjectImage(URI projectImage) {
    this.projectImage = projectImage;
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
        Objects.equals(this.status, project.status) &&
        Objects.equals(this.description, project.description) &&
        Objects.equals(this.userUUID, project.userUUID) &&
        Objects.equals(this.createdAt, project.createdAt) &&
        Objects.equals(this.updatedAt, project.updatedAt) &&
        Objects.equals(this.projectImage, project.projectImage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectUUID, name, status, description, userUUID, createdAt, updatedAt, projectImage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Project {\n");
    sb.append("    projectUUID: ").append(toIndentedString(projectUUID)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    userUUID: ").append(toIndentedString(userUUID)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    projectImage: ").append(toIndentedString(projectImage)).append("\n");
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

