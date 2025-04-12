package pm.little.courseservice.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pm.little.api.models.Finances;
import pm.little.api.models.ids.FinancesId;
import pm.little.api.repositories.FinancesRepository;
import pm.little.financesservice.FinancesService;
import pm.little.financesservice.exceptions.*;

import java.util.List;
import java.util.UUID;

@Service
public class FinancesServiceImpl implements FinancesService {

    private final FinancesRepository financesRepository;

    public FinancesServiceImpl(FinancesRepository financesRepository) {
        this.financesRepository = financesRepository;
    }

    @Override
    public List<Finances> getAllFinances() {
        return financesRepository.findAll();
    }

    @Override
    public Finances getFinancesById(UUID projectBlueprintUuid, UUID userUuid) {
        FinancesId financesId = new FinancesId(projectBlueprintUuid, userUuid);
        return financesRepository.findById(financesId)
                .orElseThrow(() -> new FinancesNotFoundException(financesId));
    }

    @Override
    @Transactional
    public Finances createFinances(Finances finances) {
        return financesRepository.save(finances);
    }

    @Override
    @Transactional
    public Finances updateFinances(UUID projectBlueprintUuid, UUID userUuid, Finances financesDetails) {
        Finances existingFinances = getFinancesById(projectBlueprintUuid, userUuid);

        // Update mutable fields
        existingFinances.setInvestorGave(financesDetails.getInvestorGave());
        existingFinances.setInvestorReturn(financesDetails.getInvestorReturn());
        existingFinances.setSpentBudget(financesDetails.getSpentBudget());
        existingFinances.setToysPlanned(financesDetails.getToysPlanned());
        existingFinances.setToysSold(financesDetails.getToysSold());
        existingFinances.setToysLeft(financesDetails.getToysLeft());
        existingFinances.setMembersBudget(financesDetails.getMembersBudget());
        existingFinances.setExpenseAmount(financesDetails.getExpenseAmount());
        existingFinances.setItemsCost(financesDetails.getItemsCost());
        existingFinances.setSoldPrice(financesDetails.getSoldPrice());

        return financesRepository.save(existingFinances);
    }

    @Override
    @Transactional
    public void deleteFinances(UUID projectBlueprintUuid, UUID userUuid) {
        Finances finances = getFinancesById(projectBlueprintUuid, userUuid);
        financesRepository.delete(finances);
    }

    @Override
    @Transactional
    public Finances calculateFinances(UUID projectBlueprintUuid, UUID userUuid) {
        Finances finances = getFinancesById(projectBlueprintUuid, userUuid);

        // Example calculation logic - adjust based on actual business rules
        float profit = finances.getInvestorReturn() - finances.getInvestorGave();
        finances.setProfit(profit);

        // Calculate budget based on spent and planned values
        float calculatedBudget = finances.getToysPlanned() > 0 ?
                (finances.getSpentBudget() / finances.getToysPlanned()) : 0;
        finances.setCalculatedBudget(calculatedBudget);

        // Calculate recommended price if applicable
        if (finances.getItemsCost() > 0 && finances.getToysPlanned() > 0) {
            float pricePerItem = (finances.getSpentBudget() + finances.getItemsCost()) / finances.getToysPlanned();
            finances.setPricePerItem(pricePerItem);
            finances.setRecommendedPrice(pricePerItem * 1.2f); // 20% markup example
        }

        return financesRepository.save(finances);
    }

    public void calculateDay1Costs(Finances finances) {
//        // Parse quantity ranges (e.g., "5-7" → min/max)
//        int feathersMin = Integer.parseInt(finances.getFeathersQuantityRange().split("-")[0]);
//        int feathersMax = Integer.parseInt(finances.getFeathersQuantityRange().split("-")[1]);
//
//        // Calculate material costs (example for feathers)
//        BigDecimal avgFeathers = BigDecimal.valueOf((feathersMin + feathersMax) / 2.0);
//        BigDecimal feathersCost = avgFeathers.multiply(finances.getFeathersCost());
//
//        finances.setTotalMaterialCosts(
//                finances.getStickCost()
//                        .add(feathersCost)
//                        .add(finances.getPomponCost())
//                        .add(finances.getRopeCost())
//        );
    }

    public void validateHelpers(Finances finances) {
//        BigDecimal totalPercentage = finances.getHelper1Percentage()
//                .add(finances.getHelper2Percentage());
//
//        if(totalPercentage.compareTo(BigDecimal.valueOf(0.5)) > 0) {
//            throw new ValidationException("Keep at least 50% profit");
//        }
//        if(totalPercentage.compareTo(BigDecimal.ONE) > 0) {
//            throw new ValidationException("Total allocation cannot exceed 100%");
//        }
    }

    public void calculateInvestorReturn(Finances finances) {
//        BigDecimal totalAsk = finances.getTotalMaterialCosts()
//                .add(finances.getOtherExpenses());
//
////        finances.setInvestorReturn(
////                totalAsk.multiply(BigDecimal.valueOf(0.1))
////        );
//
//        if(finances.getInvestorAmount().compareTo(totalAsk) < 0) {
//            throw new ValidationException("Investor contribution too low");
//        }
    }

    public void calculateRecommendedPrice(Finances finances) {
//        BigDecimal totalExpenses = finances.getTotalMaterialCosts()
//                .add(finances.getOtherExpenses());
//
//        BigDecimal margin = totalExpenses.multiply(BigDecimal.valueOf(0.2));
//        BigDecimal pricePerItem = totalExpenses.add(margin)
//                .divide(BigDecimal.valueOf(finances.getReadyItems()), 2, RoundingMode.UP);
//
//        finances.setRecommendedPrice(pricePerItem);
    }
}