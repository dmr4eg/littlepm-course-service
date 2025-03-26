package pm.little.api.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;


/**
 * UserUserUuidProjectPostRequest
 */

@JsonTypeName("_user__user_uuid__project_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-26T00:36:51.210059+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class UserUserUuidProjectPostRequest {

  private @Nullable UUID projectBlueprintUuid;

  public UserUserUuidProjectPostRequest projectBlueprintUuid(UUID projectBlueprintUuid) {
    this.projectBlueprintUuid = projectBlueprintUuid;
    return this;
  }

  /**
   * Get projectBlueprintUuid
   * @return projectBlueprintUuid
   */
  @Valid
  @Schema(name = "project_blueprint_uuid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("project_blueprint_uuid")
  public UUID getProjectBlueprintUuid() {
    return projectBlueprintUuid;
  }

  public void setProjectBlueprintUuid(UUID projectBlueprintUuid) {
    this.projectBlueprintUuid = projectBlueprintUuid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserUserUuidProjectPostRequest userUserUuidProjectPostRequest = (UserUserUuidProjectPostRequest) o;
    return Objects.equals(this.projectBlueprintUuid, userUserUuidProjectPostRequest.projectBlueprintUuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectBlueprintUuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserUserUuidProjectPostRequest {\n");
    sb.append("    projectBlueprintUuid: ").append(toIndentedString(projectBlueprintUuid)).append("\n");
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

