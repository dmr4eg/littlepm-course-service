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
 * Members
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-26T00:36:51.210059+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class Members {

  private @Nullable UUID projectBlueprintUuid;

  private @Nullable UUID userUuid;

  private @Nullable String memberName;

  public Members projectBlueprintUuid(UUID projectBlueprintUuid) {
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

  public Members userUuid(UUID userUuid) {
    this.userUuid = userUuid;
    return this;
  }

  /**
   * Get userUuid
   * @return userUuid
   */
  @Valid 
  @Schema(name = "user_uuid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("user_uuid")
  public UUID getUserUuid() {
    return userUuid;
  }

  public void setUserUuid(UUID userUuid) {
    this.userUuid = userUuid;
  }

  public Members memberName(String memberName) {
    this.memberName = memberName;
    return this;
  }

  /**
   * Get memberName
   * @return memberName
   */
  
  @Schema(name = "member_name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("member_name")
  public String getMemberName() {
    return memberName;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Members members = (Members) o;
    return Objects.equals(this.projectBlueprintUuid, members.projectBlueprintUuid) &&
        Objects.equals(this.userUuid, members.userUuid) &&
        Objects.equals(this.memberName, members.memberName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectBlueprintUuid, userUuid, memberName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Members {\n");
    sb.append("    projectBlueprintUuid: ").append(toIndentedString(projectBlueprintUuid)).append("\n");
    sb.append("    userUuid: ").append(toIndentedString(userUuid)).append("\n");
    sb.append("    memberName: ").append(toIndentedString(memberName)).append("\n");
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

