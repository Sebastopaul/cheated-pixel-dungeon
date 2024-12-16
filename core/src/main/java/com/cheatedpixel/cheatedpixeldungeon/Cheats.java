package com.cheatedpixel.cheatedpixeldungeon;

import com.watabou.utils.Bundle;

public class Cheats {

    public static final int ENABLED = 1;
    public static final int ALLOW_BADGES = 2;
    public static final int DISABLE_CHALLENGES_EFFECTS = 4;
    public static final int PREVENT_ENCHANT_LOSS = 8;
    public static final int USE_SEAL_LEVEL = 16;
    public static final int SPAWN_ALL_CRYSTAL_KEYS = 32;
    public static final int AUTO_ANTI_MAGIC_GLYPH = 64;

    public static final int MAX_VALUE = 127;

    public static final String[] BOOL_NAME_IDS = {
        "enabled",
        "allow_badges",
        "disable_challenges_effects",
        "prevent_enchant_loss",
        "use_seal_level",
        "spawn_all_crystal_keys",
        "auto_anti_magic_glyph",
    };

    public static final int[] BOOL_MASKS = {
            ENABLED,
            ALLOW_BADGES,
            DISABLE_CHALLENGES_EFFECTS,
            PREVENT_ENCHANT_LOSS,
            USE_SEAL_LEVEL,
            SPAWN_ALL_CRYSTAL_KEYS,
            AUTO_ANTI_MAGIC_GLYPH,
    };

    public static final String[] SLIDERS_NAME_IDS = {
            "cm_max_upgrade_scrolls",
            "cm_max_strength_potions",
            "cm_additional_probs_scrolls",
            "cm_additional_probs_potions",
            "cm_damage_multiplier",
            "cm_damage_reduction_multiplier",
            "cm_additional_levels_items",
            "cm_max_seal_level",
    };
    public static final int[] SLIDERS_DEF_VALUES = { 3, 2, 0, 0, 1, 1, 0, 1 };
    public static final int[] SLIDERS_MIN_VALUES = { 0, 0, 0, 0, 0, 0, 0, 0 };
    public static final int[] SLIDERS_MAX_VALUES = { 10, 10, 10, 10, 10, 10, 25, 25 };

    public static final int MAX_UPGRADE_SCROLLS = 0;
    public static final int MAX_STRENGTH_POTIONS = 1;
    public static final int ADDITIONAL_PROBS_SCROLLS = 2;
    public static final int ADDITIONAL_PROBS_POTIONS = 3;
    public static final int DAMAGE_MULTIPLIER = 4;
    public static final int DAMAGE_REDUCTION_MULTIPLIER = 5;
    public static final int ADDITIONAL_LEVELS_ITEMS = 6;
    public static final int MAX_SEAL_LEVEL = 7;

    private static int boolCheats = 0;
    private static int maxNumberOfUpgradeScrolls = 3;
    private static int maxNumberOfStrengthPotions = 2;
    private static int additionalProbsForRareScrolls = 0;
    private static int additionalProbsForRarePotions = 0;
    private static int damageMultiplier = 1;
    private static int damageReductionMultiplier = 1;
    private static int additionalLevelsOnItems = 0;
    private static int maxSealLevel = 1;

    public static void reset() {
        boolCheats                    = SPDSettings.boolCheats();
        maxNumberOfUpgradeScrolls     = SPDSettings.cheatSlider(SLIDERS_NAME_IDS[MAX_UPGRADE_SCROLLS], SLIDERS_DEF_VALUES[MAX_UPGRADE_SCROLLS], SLIDERS_MIN_VALUES[MAX_UPGRADE_SCROLLS], SLIDERS_MAX_VALUES[MAX_UPGRADE_SCROLLS]);
        maxNumberOfStrengthPotions    = SPDSettings.cheatSlider(SLIDERS_NAME_IDS[MAX_STRENGTH_POTIONS], SLIDERS_DEF_VALUES[MAX_STRENGTH_POTIONS], SLIDERS_MIN_VALUES[MAX_STRENGTH_POTIONS], SLIDERS_MAX_VALUES[MAX_STRENGTH_POTIONS]);
        additionalProbsForRareScrolls = SPDSettings.cheatSlider(SLIDERS_NAME_IDS[ADDITIONAL_PROBS_SCROLLS], SLIDERS_DEF_VALUES[ADDITIONAL_PROBS_SCROLLS], SLIDERS_MIN_VALUES[ADDITIONAL_PROBS_SCROLLS], SLIDERS_MAX_VALUES[ADDITIONAL_PROBS_SCROLLS]);
        additionalProbsForRarePotions = SPDSettings.cheatSlider(SLIDERS_NAME_IDS[ADDITIONAL_PROBS_POTIONS], SLIDERS_DEF_VALUES[ADDITIONAL_PROBS_POTIONS], SLIDERS_MIN_VALUES[ADDITIONAL_PROBS_POTIONS], SLIDERS_MAX_VALUES[ADDITIONAL_PROBS_POTIONS]);
        damageMultiplier              = SPDSettings.cheatSlider(SLIDERS_NAME_IDS[DAMAGE_MULTIPLIER], SLIDERS_DEF_VALUES[DAMAGE_MULTIPLIER], SLIDERS_MIN_VALUES[DAMAGE_MULTIPLIER], SLIDERS_MAX_VALUES[DAMAGE_MULTIPLIER]);
        damageReductionMultiplier     = SPDSettings.cheatSlider(SLIDERS_NAME_IDS[DAMAGE_REDUCTION_MULTIPLIER], SLIDERS_DEF_VALUES[DAMAGE_REDUCTION_MULTIPLIER], SLIDERS_MIN_VALUES[DAMAGE_REDUCTION_MULTIPLIER], SLIDERS_MAX_VALUES[DAMAGE_REDUCTION_MULTIPLIER]);
        additionalLevelsOnItems       = SPDSettings.cheatSlider(SLIDERS_NAME_IDS[ADDITIONAL_LEVELS_ITEMS], SLIDERS_DEF_VALUES[ADDITIONAL_LEVELS_ITEMS], SLIDERS_MIN_VALUES[ADDITIONAL_LEVELS_ITEMS], SLIDERS_MAX_VALUES[ADDITIONAL_LEVELS_ITEMS]);
        maxSealLevel                  = SPDSettings.cheatSlider(SLIDERS_NAME_IDS[MAX_SEAL_LEVEL], SLIDERS_DEF_VALUES[MAX_SEAL_LEVEL], SLIDERS_MIN_VALUES[MAX_SEAL_LEVEL], SLIDERS_MAX_VALUES[MAX_SEAL_LEVEL]);
    }

    public static void save(Bundle bundle) {
        bundle.put("cm_bool_cheats", boolCheats);
        bundle.put(SLIDERS_NAME_IDS[MAX_UPGRADE_SCROLLS], maxNumberOfUpgradeScrolls);
        bundle.put(SLIDERS_NAME_IDS[MAX_STRENGTH_POTIONS], maxNumberOfStrengthPotions);
        bundle.put(SLIDERS_NAME_IDS[ADDITIONAL_PROBS_SCROLLS], additionalProbsForRareScrolls);
        bundle.put(SLIDERS_NAME_IDS[ADDITIONAL_PROBS_POTIONS], additionalProbsForRarePotions);
        bundle.put(SLIDERS_NAME_IDS[DAMAGE_MULTIPLIER], damageMultiplier);
        bundle.put(SLIDERS_NAME_IDS[DAMAGE_REDUCTION_MULTIPLIER], damageReductionMultiplier);
        bundle.put(SLIDERS_NAME_IDS[ADDITIONAL_LEVELS_ITEMS], additionalLevelsOnItems);
        bundle.put(SLIDERS_NAME_IDS[MAX_SEAL_LEVEL], maxSealLevel);
    }

    public static void restore(Bundle bundle) {
        boolCheats                    = bundle.getInt("cm_bool_cheats");
        maxNumberOfUpgradeScrolls     = bundle.getInt(SLIDERS_NAME_IDS[MAX_UPGRADE_SCROLLS]);
        maxNumberOfStrengthPotions    = bundle.getInt(SLIDERS_NAME_IDS[MAX_STRENGTH_POTIONS]);
        additionalProbsForRareScrolls = bundle.getInt(SLIDERS_NAME_IDS[ADDITIONAL_PROBS_SCROLLS]);
        additionalProbsForRarePotions = bundle.getInt(SLIDERS_NAME_IDS[ADDITIONAL_PROBS_POTIONS]);
        damageMultiplier              = bundle.getInt(SLIDERS_NAME_IDS[DAMAGE_MULTIPLIER]);
        damageReductionMultiplier     = bundle.getInt(SLIDERS_NAME_IDS[DAMAGE_REDUCTION_MULTIPLIER]);
        additionalLevelsOnItems       = bundle.getInt(SLIDERS_NAME_IDS[ADDITIONAL_LEVELS_ITEMS]);
        maxSealLevel                  = bundle.getInt(SLIDERS_NAME_IDS[MAX_SEAL_LEVEL]);
    }

    public static void boolCheats( int value ) {
        boolCheats = value;
    }

    public static boolean isCheated( int mask ) {
        return (boolCheats & mask) != 0;
    }

    public static void maxNumberOfUpgradeScrolls(int value) {
        maxNumberOfUpgradeScrolls = value;
    }

    public static int maxNumberOfUpgradeScrolls() {
        return isCheated(ENABLED) ? maxNumberOfUpgradeScrolls : 3;
    }

    public static void maxNumberOfStrengthPotions(int value) {
        maxNumberOfStrengthPotions = value;
    }

    public static int maxNumberOfStrengthPotions() {
        return isCheated(ENABLED) ? maxNumberOfStrengthPotions : 2;
    }

    public static void additionalProbsForRareScrolls(int value) {
        additionalProbsForRareScrolls = value;
    }

    public static int additionalProbsForRareScrolls() {
        return isCheated(ENABLED) ? additionalProbsForRareScrolls : 0;
    }

    public static void additionalProbsForRarePotions(int value) {
        additionalProbsForRarePotions = value;
    }

    public static int additionalProbsForRarePotions() {
        return isCheated(ENABLED) ? additionalProbsForRarePotions : 0;
    }

    public static void damageMultiplier(int value) {
        damageMultiplier = value;
    }

    public static int damageMultiplier() {
        return isCheated(ENABLED) ? 1 + damageMultiplier / 10 : 1;
    }

    public static void damageReductionMultiplier(int value) {
        damageReductionMultiplier = value;
    }

    public static int damageReductionMultiplier() {
        return isCheated(ENABLED) ? 1 + damageReductionMultiplier / 10 : 1;
    }

    public static void additionalLevelsOnItems(int value) {
        additionalLevelsOnItems = value;
    }

    public static int additionalLevelsOnItems() {
        return isCheated(ENABLED) ? additionalLevelsOnItems : 0;
    }

    public static void maxSealLevel(int value) {
        maxSealLevel = value;
    }

    public static int maxSealLevel() {
        return isCheated(ENABLED) ? maxSealLevel : 1;
    }
}
