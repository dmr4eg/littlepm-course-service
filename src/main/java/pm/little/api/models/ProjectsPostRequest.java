package pm.little.api.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.UUID;

import jakarta.validation.Valid;
import org.springframework.lang.Nullable;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ProjectsPostRequest
 */

@JsonTypeName("_projects_post_request")
public class ProjectsPostRequest {

  private @Nullable UUID blueprintUUID;

  private @Nullable String name;

  public ProjectsPostRequest blueprintUUID(UUID blueprintUUID) {
    this.blueprintUUID = blueprintUUID;
    return this;
  }

  /**
   * Get blueprintUUID
   * @return blueprintUUID
   */
  @Valid
  @Schema(name = "blueprintUUID", example = "550e8400-e29b-41d4-a716-446655440000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("blueprintUUID")
  public UUID getBlueprintUUID() {
    return blueprintUUID;
  }

  public void setBlueprintUUID(UUID blueprintUUID) {
    this.blueprintUUID = blueprintUUID;
  }

  public ProjectsPostRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectsPostRequest projectsPostRequest = (ProjectsPostRequest) o;
    return Objects.equals(this.blueprintUUID, projectsPostRequest.blueprintUUID) &&
        Objects.equals(this.name, projectsPostRequest.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blueprintUUID, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectsPostRequest {\n");
    sb.append("    blueprintUUID: ").append(toIndentedString(blueprintUUID)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

