package com.shatteredpixel.shatteredpixeldungeon;

import com.watabou.utils.Bundle;

public class Cheats {
    private static boolean enabled = false;
    private static boolean allowBadges = false;
    private static boolean disableChallengesEffects = true;
    private static boolean preventEnchantLoss = true;
    private static boolean disableRestraintsOnUpgradeScrolls = true;
    private static boolean disableRestraintsOnStrengthPotions = true;
    private static int additionalProbsForRareScrolls = 0;
    private static int additionalProbsForRarePotions = 0;
    private static int damageMultiplier = 1;
    private static int damageReductionMultiplier = 1;
    private static int additionalLevelsOnItems = 0;

    public static void reset() {
        enabled = SPDSettings.cheatMode();

        if (!enabled) {
            return;
        }

        allowBadges = SPDSettings.cmAllowBadges();
        disableChallengesEffects = SPDSettings.cmDisableChallenges();
        preventEnchantLoss = SPDSettings.cmPreventEnchantLoss();
        disableRestraintsOnUpgradeScrolls = SPDSettings.cmDisableRestraintsScrolls();
        disableRestraintsOnStrengthPotions = SPDSettings.cmDisableRestraintsPotions();
        additionalProbsForRareScrolls = SPDSettings.cmAdditionalProbsForRareScrolls();
        additionalProbsForRarePotions = SPDSettings.cmAdditionalProbsForRarePotions();
        damageMultiplier = SPDSettings.cmDamageMultiplier();
        damageReductionMultiplier = SPDSettings.cmDamageReductionMultiplier();
        additionalLevelsOnItems = SPDSettings.cmAdditionalLevelsOnItems();
    }

    public static void save(Bundle bundle) {
        bundle.put("cm_enabled", enabled);

        if (!enabled) {
            return;
        }

        bundle.put("cm_allow_badges", allowBadges);
        bundle.put("cm_disable_challenges_effects", disableChallengesEffects);
        bundle.put("cm_prevent_enchant_loss", preventEnchantLoss);
        bundle.put("cm_disable_restraints_scrolls", disableRestraintsOnUpgradeScrolls);
        bundle.put("cm_disable_restraints_strength_potions", disableRestraintsOnStrengthPotions);
        bundle.put("cm_additional_probs_for_rare_scrolls", additionalProbsForRareScrolls);
        bundle.put("cm_additional_probs_for_rare_potions", additionalProbsForRarePotions);
        bundle.put("cm_damage_multiplier", damageMultiplier);
        bundle.put("cm_damage_reduction_multiplier", damageReductionMultiplier);
        bundle.put("cm_additional_levels_on_items", additionalLevelsOnItems);
    }

    public static void restore(Bundle bundle) {
        enabled = bundle.getBoolean("cm_enabled");

        if (!enabled) {
            return;
        }

        allowBadges = bundle.getBoolean("cm_allow_badges");
        disableChallengesEffects = bundle.getBoolean("cm_disable_challenges_effects");
        preventEnchantLoss = bundle.getBoolean("cm_prevent_enchant_loss");
        disableRestraintsOnUpgradeScrolls = bundle.getBoolean("cm_disable_restraints_scrolls");
        disableRestraintsOnStrengthPotions = bundle.getBoolean("cm_disable_restraints_strength_potions");
        additionalProbsForRareScrolls = bundle.getInt("cm_additional_probs_for_rare_scrolls");
        additionalProbsForRarePotions = bundle.getInt("cm_additional_probs_for_rare_potions");
        damageMultiplier = bundle.getInt("cm_damage_multiplier");
        damageReductionMultiplier = bundle.getInt("cm_damage_reduction_multiplier");
        additionalLevelsOnItems = bundle.getInt("cm_additional_levels_on_items");
    }

    public static void enabled(boolean value) {
        enabled = value;
    }

    public static boolean enabled() {
        return enabled;
    }

    public static void allowBadges(boolean value) {
        allowBadges = value;
    }

    public static boolean allowBadges() {
        return enabled && allowBadges;
    }

    public static void disableChallengesEffects(boolean value) {
        disableChallengesEffects = value;
    }

    public static boolean disableChallengesEffects() {
        return enabled && disableChallengesEffects;
    }

    public static void preventEnchantLoss(boolean value) {
        preventEnchantLoss = value;
    }

    public static boolean preventEnchantLoss() {
        return enabled && preventEnchantLoss;
    }

    public static void disableRestraintsOnUpgradeScrolls(boolean value) {
        disableRestraintsOnUpgradeScrolls = value;
    }

    public static boolean disableRestraintsOnUpgradeScrolls() {
        return enabled && disableRestraintsOnUpgradeScrolls;
    }

    public static void disableRestraintsOnStrengthPotions(boolean value) {
        disableRestraintsOnStrengthPotions = value;
    }

    public static boolean disableRestraintsOnStrengthPotions() {
        return enabled && disableRestraintsOnStrengthPotions;
    }

    public static void additionalProbsForRareScrolls(int value) {
        additionalProbsForRareScrolls = value;
    }

    public static int additionalProbsForRareScrolls() {
        return enabled ? additionalProbsForRareScrolls : 0;
    }

    public static void additionalProbsForRarePotions(int value) {
        additionalProbsForRarePotions = value;
    }

    public static int additionalProbsForRarePotions() {
        return enabled ? additionalProbsForRarePotions : 0;
    }

    public static void damageMultiplier(int value) {
        damageMultiplier = value;
    }

    public static int damageMultiplier() {
        return enabled ? damageMultiplier : 1;
    }

    public static void damageReductionMultiplier(int value) {
        damageReductionMultiplier = value;
    }

    public static int damageReductionMultiplier() {
        return enabled ? damageReductionMultiplier : 1;
    }

    public static void additionalLevelsOnItems(int value) {
        additionalLevelsOnItems = value;
    }

    public static int additionalLevelsOnItems() {
        return enabled ? additionalLevelsOnItems : 0;
    }
}
