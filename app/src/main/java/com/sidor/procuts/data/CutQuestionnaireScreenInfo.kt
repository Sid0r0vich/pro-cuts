package com.sidor.procuts.data

import com.sidor.procuts.R
import com.sidor.procuts.ui.screens.screentypes.CutQuestionnaireScreenType

data class CutQuestionnaireScreenInfo(
    val paramName: String,
    val question: String,
    val paramList: List<String>,
    val screenType: CutQuestionnaireScreenType
)

//data class CutQuestionnaireScreenInfo(
//    val paramLabelId: Int,
//    val questionId: Int,
//    val paramIdList: List<Int>,
//    val paramName: String,
//    val screenType: CutQuestionnaireScreenType
//)
//
//val cutQuestionnaireScreenInfoLists: List<CutQuestionnaireScreenInfo> = listOf(
//    CutQuestionnaireScreenInfo(
//        paramLabelId = R.string.client_age_group,
//        questionId = R.string.question_age_group,
//        paramIdList = ageGroupList,
//        paramName = "age",
//        screenType = CutQuestionnaireScreenType.Age
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId = R.string.cut_frequency,
//        questionId = R.string.question_cut_frequency,
//        paramIdList = cutFrequencyList,
//        paramName = "cutFrequency",
//        screenType = CutQuestionnaireScreenType.CutFrequency
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId = R.string.cut_head_form,
//        questionId = R.string.question_head_form,
//        paramIdList = headFormList,
//        paramName = "headForm",
//        screenType = CutQuestionnaireScreenType.HeadForm
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId = R.string.cut_hair_struct,
//        questionId = R.string.question_hair_struct,
//        paramIdList = hairStructList,
//        paramName = "hairStruct",
//        screenType = CutQuestionnaireScreenType.HairStruct
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId = R.string.cut_hair_thickness,
//        questionId = R.string.question_hair_thickness,
//        paramIdList = hairThicknessList,
//        paramName = "hairThickness",
//        screenType = CutQuestionnaireScreenType.HairThickness
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId = R.string.cut_hair_len,
//        questionId = R.string.question_hair_len,
//        paramIdList = hairLenList,
//        paramName = "hairLen",
//        screenType = CutQuestionnaireScreenType.HairLen
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId = R.string.cut_scalp_type,
//        questionId = R.string.question_scalp_type,
//        paramIdList = scalpTypeList,
//        paramName = "scalpType",
//        screenType = CutQuestionnaireScreenType.ScalpType
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId = R.string.cut_hair_damage_level,
//        questionId = R.string.question_hair_damage_level,
//        paramIdList = hairDamageLevelList,
//        paramName = "hairDamageLevel",
//        screenType = CutQuestionnaireScreenType.HairDamageLevel
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId = R.string.chemical_procedures,
//        questionId = R.string.question_chemical_procedures,
//        paramIdList = chemicalProceduresList,
//        paramName = "chemicalProcedures",
//        screenType = CutQuestionnaireScreenType.ChemicalProcedures
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId=R.string.washing_frequency,
//        questionId=R.string.question_washing_frequency,
//        paramIdList=washingFrequencyList,
//        paramName="washingFrequency",
//        screenType=CutQuestionnaireScreenType.WashingFrequency
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId=R.string.average_styling_time,
//        questionId=R.string.question_average_styling_time,
//        paramIdList=averageStylingTimeList,
//        paramName="averageStylingTime",
//        screenType=CutQuestionnaireScreenType.AverageStylingTime
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId=R.string.styling_tools,
//        questionId=R.string.question_styling_tools,
//        paramIdList=stylingToolsList,
//        paramName="stylingTools",
//        screenType=CutQuestionnaireScreenType.StylingTools
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId=R.string.lifestyle,
//        questionId=R.string.question_lifestyle,
//        paramIdList=lifestyleList,
//        paramName="lifestyle",
//        screenType=CutQuestionnaireScreenType.Lifestyle
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId=R.string.temperament_style,
//        questionId=R.string.question_temperament_style,
//        paramIdList=temperamentStyleList,
//        paramName="temperamentStyle",
//        screenType=CutQuestionnaireScreenType.TemperamentStyle
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId=R.string.hair_color,
//        questionId=R.string.question_hair_color,
//        paramIdList=hairColorList,
//        paramName="hairColor",
//        screenType=CutQuestionnaireScreenType.HairColor
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId=R.string.body_type,
//        questionId=R.string.question_body_type,
//        paramIdList=bodyTypeList,
//        paramName="bodyType",
//        screenType=CutQuestionnaireScreenType.BodyType
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId = R.string.hair_care_routine,
//        questionId = R.string.question_hair_care_routine,
//        paramIdList = hairCareRoutineList,
//        paramName = "hairCareRoutine",
//        screenType = CutQuestionnaireScreenType.HairCareRoutine
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId = R.string.preferred_styles,
//        questionId = R.string.question_preferred_styles,
//        paramIdList = preferredStylesList,
//        paramName = "preferredStyles",
//        screenType = CutQuestionnaireScreenType.PreferredStyles
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId = R.string.allergies,
//        questionId = R.string.question_allergies,
//        paramIdList = allergiesList,
//        paramName = "allergies",
//        screenType = CutQuestionnaireScreenType.Allergies
//    ),
//    CutQuestionnaireScreenInfo(
//        paramLabelId = R.string.product_usage,
//        questionId = R.string.question_product_usage,
//        paramIdList = productUsageList,
//        paramName = "productUsage",
//        screenType = CutQuestionnaireScreenType.ProductUsage
//    )
//)