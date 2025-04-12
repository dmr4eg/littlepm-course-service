package pm.little.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import pm.little.api.models.ids.FinancesId;

import java.util.Objects;


/**
 * Entity for user finances on a specific project
 */
@Entity
@Schema(name = "Finances", description = "Entity for user finances on a specific project")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
public class Finances {
  @EmbeddedId
  private FinancesId id;

  private @NotNull Float investorGave;

  private @NotNull Float investorReturn;

  private @NotNull Float spentBudget;

  private @NotNull Float toysPlanned;

  private @NotNull Float toysSold;

  private @NotNull Float toysLeft;

  private @NotNull Float membersBudget;

  private @NotNull Float profit;

  private @NotNull Float expenseAmount;

  private @NotNull Float calculatedBudget;

  private @NotNull Float itemsCost;

  private @NotNull Float recommendedPrice;

  private @NotNull Float pricePerItem;

  private @NotNull Float soldPrice;

  public Finances() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Finances(FinancesId id) {
    this.id = id;
  }

  public Finances id(FinancesId id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @NotNull @Valid 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public FinancesId getId() {
    return id;
  }

  public void setId(FinancesId id) {
    this.id = id;
  }

  public Finances investorGave(Float investorGave) {
    this.investorGave = investorGave;
    return this;
  }

  /**
   * Get investorGave
   * @return investorGave
   */
  
  @Schema(name = "investor_gave", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("investor_gave")
  public Float getInvestorGave() {
    return investorGave;
  }

  public void setInvestorGave(Float investorGave) {
    this.investorGave = investorGave;
  }

  public Finances investorReturn(Float investorReturn) {
    this.investorReturn = investorReturn;
    return this;
  }

  /**
   * Get investorReturn
   * @return investorReturn
   */
  
  @Schema(name = "investor_return", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("investor_return")
  public Float getInvestorReturn() {
    return investorReturn;
  }

  public void setInvestorReturn(Float investorReturn) {
    this.investorReturn = investorReturn;
  }

  public Finances spentBudget(Float spentBudget) {
    this.spentBudget = spentBudget;
    return this;
  }

  /**
   * Get spentBudget
   * @return spentBudget
   */
  
  @Schema(name = "spent_budget", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("spent_budget")
  public Float getSpentBudget() {
    return spentBudget;
  }

  public void setSpentBudget(Float spentBudget) {
    this.spentBudget = spentBudget;
  }

  public Finances toysPlanned(Float toysPlanned) {
    this.toysPlanned = toysPlanned;
    return this;
  }

  /**
   * Get toysPlanned
   * @return toysPlanned
   */
  
  @Schema(name = "toys_planned", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("toys_planned")
  public Float getToysPlanned() {
    return toysPlanned;
  }

  public void setToysPlanned(Float toysPlanned) {
    this.toysPlanned = toysPlanned;
  }

  public Finances toysSold(Float toysSold) {
    this.toysSold = toysSold;
    return this;
  }

  /**
   * Get toysSold
   * @return toysSold
   */
  
  @Schema(name = "toys_sold", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("toys_sold")
  public Float getToysSold() {
    return toysSold;
  }

  public void setToysSold(Float toysSold) {
    this.toysSold = toysSold;
  }

  public Finances toysLeft(Float toysLeft) {
    this.toysLeft = toysLeft;
    return this;
  }

  /**
   * Get toysLeft
   * @return toysLeft
   */
  
  @Schema(name = "toys_left", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("toys_left")
  public Float getToysLeft() {
    return toysLeft;
  }

  public void setToysLeft(Float toysLeft) {
    this.toysLeft = toysLeft;
  }

  public Finances membersBudged(Float membersBudged) {
    this.membersBudget = membersBudged;
    return this;
  }

  /**
   * Get membersBudged
   * @return membersBudged
   */
  
  @Schema(name = "members_budged", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("members_budged")
  public Float getMembersBudget() {
    return membersBudget;
  }

  public void setMembersBudget(Float membersBudged) {
    this.membersBudget = membersBudged;
  }

  public Finances profit(Float profit) {
    this.profit = profit;
    return this;
  }

  /**
   * Get profit
   * @return profit
   */
  
  @Schema(name = "profit", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("profit")
  public Float getProfit() {
    return profit;
  }

  public void setProfit(Float profit) {
    this.profit = profit;
  }

  public Finances expenseAmount(Float expenseAmount) {
    this.expenseAmount = expenseAmount;
    return this;
  }

  /**
   * Get expenseAmount
   * @return expenseAmount
   */
  
  @Schema(name = "expense_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expense_amount")
  public Float getExpenseAmount() {
    return expenseAmount;
  }

  public void setExpenseAmount(Float expenseAmount) {
    this.expenseAmount = expenseAmount;
  }

  public Finances calculatedBudget(Float calculatedBudget) {
    this.calculatedBudget = calculatedBudget;
    return this;
  }

  /**
   * Get calculatedBudget
   * @return calculatedBudget
   */
  
  @Schema(name = "calculated_budget", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("calculated_budget")
  public Float getCalculatedBudget() {
    return calculatedBudget;
  }

  public void setCalculatedBudget(Float calculatedBudget) {
    this.calculatedBudget = calculatedBudget;
  }

  public Finances itemsCost(Float itemsCost) {
    this.itemsCost = itemsCost;
    return this;
  }

  /**
   * Get itemsCost
   * @return itemsCost
   */
  
  @Schema(name = "items_cost", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("items_cost")
  public Float getItemsCost() {
    return itemsCost;
  }

  public void setItemsCost(Float itemsCost) {
    this.itemsCost = itemsCost;
  }

  public Finances recommendedPrice(Float recommendedPrice) {
    this.recommendedPrice = recommendedPrice;
    return this;
  }

  /**
   * Get recommendedPrice
   * @return recommendedPrice
   */
  
  @Schema(name = "recommended_price", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("recommended_price")
  public Float getRecommendedPrice() {
    return recommendedPrice;
  }

  public void setRecommendedPrice(Float recommendedPrice) {
    this.recommendedPrice = recommendedPrice;
  }

  public Finances pricePerItem(Float pricePerItem) {
    this.pricePerItem = pricePerItem;
    return this;
  }

  /**
   * Get pricePerItem
   * @return pricePerItem
   */
  
  @Schema(name = "price_per_item", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("price_per_item")
  public Float getPricePerItem() {
    return pricePerItem;
  }

  public void setPricePerItem(Float pricePerItem) {
    this.pricePerItem = pricePerItem;
  }

  public Finances soldPrice(Float soldPrice) {
    this.soldPrice = soldPrice;
    return this;
  }

  /**
   * Get soldPrice
   * @return soldPrice
   */
  
  @Schema(name = "sold_price", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sold_price")
  public Float getSoldPrice() {
    return soldPrice;
  }

  public void setSoldPrice(Float soldPrice) {
    this.soldPrice = soldPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Finances finances = (Finances) o;
    return Objects.equals(this.id, finances.id) &&
        Objects.equals(this.investorGave, finances.investorGave) &&
        Objects.equals(this.investorReturn, finances.investorReturn) &&
        Objects.equals(this.spentBudget, finances.spentBudget) &&
        Objects.equals(this.toysPlanned, finances.toysPlanned) &&
        Objects.equals(this.toysSold, finances.toysSold) &&
        Objects.equals(this.toysLeft, finances.toysLeft) &&
        Objects.equals(this.membersBudget, finances.membersBudget) &&
        Objects.equals(this.profit, finances.profit) &&
        Objects.equals(this.expenseAmount, finances.expenseAmount) &&
        Objects.equals(this.calculatedBudget, finances.calculatedBudget) &&
        Objects.equals(this.itemsCost, finances.itemsCost) &&
        Objects.equals(this.recommendedPrice, finances.recommendedPrice) &&
        Objects.equals(this.pricePerItem, finances.pricePerItem) &&
        Objects.equals(this.soldPrice, finances.soldPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, investorGave, investorReturn, spentBudget, toysPlanned, toysSold, toysLeft, membersBudget, profit, expenseAmount, calculatedBudget, itemsCost, recommendedPrice, pricePerItem, soldPrice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Finances {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    investorGave: ").append(toIndentedString(investorGave)).append("\n");
    sb.append("    investorReturn: ").append(toIndentedString(investorReturn)).append("\n");
    sb.append("    spentBudget: ").append(toIndentedString(spentBudget)).append("\n");
    sb.append("    toysPlanned: ").append(toIndentedString(toysPlanned)).append("\n");
    sb.append("    toysSold: ").append(toIndentedString(toysSold)).append("\n");
    sb.append("    toysLeft: ").append(toIndentedString(toysLeft)).append("\n");
    sb.append("    membersBudged: ").append(toIndentedString(membersBudget)).append("\n");
    sb.append("    profit: ").append(toIndentedString(profit)).append("\n");
    sb.append("    expenseAmount: ").append(toIndentedString(expenseAmount)).append("\n");
    sb.append("    calculatedBudget: ").append(toIndentedString(calculatedBudget)).append("\n");
    sb.append("    itemsCost: ").append(toIndentedString(itemsCost)).append("\n");
    sb.append("    recommendedPrice: ").append(toIndentedString(recommendedPrice)).append("\n");
    sb.append("    pricePerItem: ").append(toIndentedString(pricePerItem)).append("\n");
    sb.append("    soldPrice: ").append(toIndentedString(soldPrice)).append("\n");
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

