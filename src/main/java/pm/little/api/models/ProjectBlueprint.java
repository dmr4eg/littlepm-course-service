package pm.little.api.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import pm.little.api.models.enums.DifficultyEnum;
import pm.little.api.models.enums.StyleEnum;


import java.util.*;

/**
 * ProjectBlueprint
 */

@Entity
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class ProjectBlueprint {

  @Id
  @NotNull
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID projectBlueprintUuid;

  private @Nullable String posterUrl;

  private @Nullable String welcomeVideoUrl;

  private String title;

  private @Nullable String description;

  /**
   * Gets or Sets difficulty
   */


  private @Nullable DifficultyEnum difficulty;

  /**
   * Gets or Sets style
   */


  private @Nullable StyleEnum style;

  public ProjectBlueprint() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProjectBlueprint(UUID projectBlueprintUuid, String title) {
    this.projectBlueprintUuid = projectBlueprintUuid;
    this.title = title;
  }

  public ProjectBlueprint projectBlueprintUuid(UUID projectBlueprintUuid) {
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

  public ProjectBlueprint posterUrl(String posterUrl) {
    this.posterUrl = posterUrl;
    return this;
  }

  /**
   * URL for the project poster image
   * @return posterUrl
   */
  
  @Schema(name = "poster_url", description = "URL for the project poster image", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("poster_url")
  public String getPosterUrl() {
    return posterUrl;
  }

  public void setPosterUrl(String posterUrl) {
    this.posterUrl = posterUrl;
  }

  public ProjectBlueprint welcomeVideoUrl(String welcomeVideoUrl) {
    this.welcomeVideoUrl = welcomeVideoUrl;
    return this;
  }

  /**
   * URL for the project introduction video
   * @return welcomeVideoUrl
   */
  
  @Schema(name = "welcome_video_url", description = "URL for the project introduction video", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("welcome_video_url")
  public String getWelcomeVideoUrl() {
    return welcomeVideoUrl;
  }

  public void setWelcomeVideoUrl(String welcomeVideoUrl) {
    this.welcomeVideoUrl = welcomeVideoUrl;
  }

  public ProjectBlueprint title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   */
  @NotNull 
  @Schema(name = "title", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ProjectBlueprint description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  
  @Schema(name = "description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProjectBlueprint difficulty(DifficultyEnum difficulty) {
    this.difficulty = difficulty;
    return this;
  }

  /**
   * Get difficulty
   * @return difficulty
   */
  
  @Schema(name = "difficulty", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("difficulty")
  public DifficultyEnum getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(DifficultyEnum difficulty) {
    this.difficulty = difficulty;
  }

  public ProjectBlueprint style(StyleEnum style) {
    this.style = style;
    return this;
  }

  /**
   * Get style
   * @return style
   */
  
  @Schema(name = "style", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("style")
  public StyleEnum getStyle() {
    return style;
  }

  public void setStyle(StyleEnum style) {
    this.style = style;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectBlueprint projectBlueprint = (ProjectBlueprint) o;
    return Objects.equals(this.projectBlueprintUuid, projectBlueprint.projectBlueprintUuid) &&
        Objects.equals(this.posterUrl, projectBlueprint.posterUrl) &&
        Objects.equals(this.welcomeVideoUrl, projectBlueprint.welcomeVideoUrl) &&
        Objects.equals(this.title, projectBlueprint.title) &&
        Objects.equals(this.description, projectBlueprint.description) &&
        Objects.equals(this.difficulty, projectBlueprint.difficulty) &&
        Objects.equals(this.style, projectBlueprint.style);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectBlueprintUuid, posterUrl, welcomeVideoUrl, title, description, difficulty, style);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectBlueprint {\n");
    sb.append("    projectBlueprintUuid: ").append(toIndentedString(projectBlueprintUuid)).append("\n");
    sb.append("    posterUrl: ").append(toIndentedString(posterUrl)).append("\n");
    sb.append("    welcomeVideoUrl: ").append(toIndentedString(welcomeVideoUrl)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    difficulty: ").append(toIndentedString(difficulty)).append("\n");
    sb.append("    style: ").append(toIndentedString(style)).append("\n");
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

