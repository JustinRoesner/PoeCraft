
public class CalcCraftCost {
	Valueable valueable;
	ItemReporter itemReporter = new ItemReporter();
	public CalcCraftCost(Valueable valueable){
		this.valueable = valueable;
	}
	
	public float QuicksilverFlask(Flask flask){
		FindModTotals findModTotals = new FindModTotals();
		final int prefixIWant = 2; //Surgeon's. 20% chance to gain a Flask Charge when you deal a Critical Strike.
									//Perpetual. (20-40)% increased Charge Recovery.
		final int suffixIWant = 1; //of Adrenaline. (20-30)% increased Movement Speed during Flask effect.
		float correctPrefixChance, correctSuffixChance, chanceOfBothCorrect, numberOfCraftsToMakeOne,
		alterationOrbsNeeded, augmentationOrbsNeeded, totalChaosOrbCost;
		
		correctPrefixChance = (prefixIWant / findModTotals.prefixesAvailable(flask)) * 100;
		correctSuffixChance = (suffixIWant / findModTotals.suffixesAvailable(flask)) * 100;
		chanceOfBothCorrect = correctPrefixChance / correctSuffixChance;
	
		//report total chance
		itemReporter.ChanceToCraft(correctPrefixChance, correctSuffixChance, chanceOfBothCorrect);
		
		numberOfCraftsToMakeOne = 100 / chanceOfBothCorrect;
		
		alterationOrbsNeeded = numberOfCraftsToMakeOne;
		augmentationOrbsNeeded = numberOfCraftsToMakeOne / correctPrefixChance;
		totalChaosOrbCost = valueable.AlterationOrbValue(alterationOrbsNeeded) + valueable.AugmentationOrbValue(augmentationOrbsNeeded);
		
		//report currency usage
		itemReporter.CurrencyUsage(alterationOrbsNeeded, augmentationOrbsNeeded, totalChaosOrbCost);
		
		return totalChaosOrbCost;
	}
}
