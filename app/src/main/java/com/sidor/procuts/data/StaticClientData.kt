package com.sidor.procuts.data

import com.sidor.procuts.R
import java.text.SimpleDateFormat

val readableDMYDateFormat = SimpleDateFormat("dd MMMM yyyy")
var DMYDateFormat = SimpleDateFormat("dd-MM-yyyy")

val ageGroupList = listOf(
    R.string.age_group_0_12,
    R.string.age_group_13_17,
    R.string.age_group_18_25,
    R.string.age_group_26_35,
    R.string.age_group_36_45,
    R.string.age_group_46_plus
)

val cutFrequencyList = listOf(
    R.string.cut_frequency_weekly,
    R.string.cut_frequency_every_two_weeks,
    R.string.cut_frequency_monthly,
    R.string.cut_frequency_more_rarely
)

val headFormList = listOf(
    R.string.head_form_circle,
    R.string.head_form_oval,
    R.string.head_form_square,
    R.string.head_form_heart,
    R.string.head_form_triangle,
    R.string.head_form_diamond,
    R.string.head_form_oblong
)

val hairStructList = listOf(
    R.string.hair_structure_straight,
    R.string.hair_structure_wavy,
    R.string.hair_structure_curly,
    R.string.hair_structure_spiral
)

val hairThicknessList = listOf(
    R.string.hair_thickness_low,
    R.string.hair_thickness_medium,
    R.string.hair_thickness_high
)

val hairLenList = listOf(
    R.string.hair_length_short,
    R.string.hair_length_medium,
    R.string.hair_length_long
)

val scalpTypeList = listOf(
    R.string.scalp_type_dry,
    R.string.scalp_type_normal,
    R.string.scalp_type_oily,
    R.string.scalp_type_sensitive
)

val hairDamageLevelList = listOf(
    R.string.hair_damage_level_healthy,
    R.string.hair_damage_level_slightly_damaged,
    R.string.hair_damage_level_severely_damaged
)

val chemicalProceduresList = listOf(
    R.string.chemical_procedure_coloring,
    R.string.chemical_procedure_highlighting,
    R.string.chemical_procedure_straightening,
    R.string.chemical_procedure_none
)

val washingFrequencyList = listOf(
    R.string.washing_frequency_daily,
    R.string.washing_frequency_every_other_day,
    R.string.washing_frequency_two_to_three_times_a_week,
    R.string.washing_frequency_less_frequently
)

val averageStylingTimeList = listOf(
    R.string.average_styling_time_a_couple_of_minutes,
    R.string.average_styling_time_3_10_minutes,
    R.string.average_styling_time_more_than_10_minutes,
    R.string.average_styling_time_none,
)

val stylingToolsList = listOf(
    R.string.styling_tool_hair_dryer,
    R.string.styling_tool_flat_iron,
    R.string.styling_tool_curling_iron,
    R.string.styling_tool_brush
)

val lifestyleList = listOf(
    R.string.lifestyle_sporty,
    R.string.lifestyle_business,
    R.string.lifestyle_calm,
    R.string.lifestyle_creative
)

val temperamentStyleList = listOf(
    R.string.temperament_experimentalist,
    R.string.temperament_minimalist,
    R.string.temperament_conservative
)

val hairColorList = listOf(
    R.string.hair_color_blonde,
    R.string.hair_color_brunette,
    R.string.hair_color_black_haired,
    R.string.hair_color_ginger
)

val bodyTypeList = listOf(
    R.string.body_type_thin,
    R.string.body_type_average,
    R.string.body_type_sporty,
    R.string.body_type_plump
)

val hairCareRoutineList = listOf(
    R.string.hair_care_shampoo,
    R.string.hair_care_conditioner,
    R.string.hair_care_none
)

val preferredStylesList = listOf(
    R.string.style_straight,
    R.string.style_curly,
    R.string.style_none
)

val allergiesList = listOf(
    R.string.allergy_nuts,
    R.string.allergy_perfume,
    R.string.allergy_none
)

val productUsageList = listOf(
    R.string.product_usage_weekly,
    R.string.product_usage_on_wet_hair,
    R.string.product_usage_none
)

val nameToLabelId = cutQuestionnaireScreenInfoLists
    .associate { screen ->
        screen.paramName to screen.paramLabelId
    }.toMutableMap()
    .also {
        it["cutName"] = R.string.cut_name
    }
