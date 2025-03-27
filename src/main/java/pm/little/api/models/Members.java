package pm.little.api.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Generated;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import pm.little.api.models.ids.MembersId;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * Entity for user membership in a specific project
 */
@Entity
@Schema(name = "Members", description = "Entity for user membership in a specific project")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class Members {
  @EmbeddedId
  @NotNull
  private MembersId id;

  private String memberName;

  public Members() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Members(MembersId id, String memberName) {
    this.id = id;
    this.memberName = memberName;
  }

  public Members id(MembersId id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @NotNull
  @Valid
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public MembersId getId() {
    return id;
  }

  public void setId(MembersId id) {
    this.id = id;
  }

  public Members memberName(String memberName) {
    this.memberName = memberName;
    return this;
  }

  /**
   * Get memberName
   * @return memberName
   */
  @NotNull 
  @Schema(name = "member_name", requiredMode = Schema.RequiredMode.REQUIRED)
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
    return Objects.equals(this.id, members.id) &&
        Objects.equals(this.memberName, members.memberName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, memberName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Members {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

