package com.sidor.procuts.data

import com.sidor.procuts.R
import java.text.SimpleDateFormat

var cliensList = mutableListOf(
    Client(firstName = "Jason", lastName = "Statham"),
    Client(firstName = "Dwayne", middleName =  "Douglas", lastName =  "Johnson"),
    Client(firstName = "Илья", middleName = "Игоревич", lastName =  "Муромцев"),
    Client(firstName = "Дмитрий", middleName = "Сергеевич", lastName = "Шалымов"),
    Client(firstName = "Владимир", lastName = "Путин"),
    Client(firstName = "Евгений", lastName =  "Туаев"),
    Client(firstName = "Роберт", lastName = "Смайт"),
    Client(firstName = "Павел", lastName = "Скаков"),
    Client(firstName = "Мистер", lastName = "Бист"),
    Client(firstName = "Иван", lastName = "Иванов"),
    Client(firstName = "Иван", lastName = "Петрухин"),
)

val allCuts = mapOf(
    0 to Cut(0, "Classic", "Классическая модельная стрижка представляет собой длину волос около пяти сантиметров, при этом присутствуют в основном ровные линии. Такая стрижка выглядит сдержанно и аккуратно, отлично подходит деловым мужчинам и тем, которые не хотят чрезмерного внимания к своему внешнему виду.", R.drawable.classic),
    1 to Cut(1, "Undercut", "Для стрижки характерна почти одинаковая длина волос по всей поверхности головы. Классический варианта – это выбритые виски и немного удлиненные волосы на верхней части. Андеркат в таком исполнении подходит практически всем. Он придаст образу мужественности и делового стиля.", R.drawable.undercut),
    2 to Cut(2, "Curtains", "Мужская стрижка, предполагающая прямой центральный пробор и симметричные пряди по обеим сторонам. При этом длина стрижки, наличие или отсутствие челки, градуировка никаким образом не регламентированы — здесь у нас с вами полная свобода.", R.drawable.curtains),
    3 to Cut(3, "Bold", "Цель данной стрижки — полностью обнажить кожу головы, сбрив все волосы. Стрижка под 0 способствует созданию образа уверенного и сильного человека, своему обладателю придает максимум мужественности, помогает почувствовать себя увереннее и подчеркнуть индивидуальность.", R.drawable.bold),
)

var cutNamesToId = allCuts.entries.associate { (key, value) -> value.name to key }

val readableDMYDateFormat = SimpleDateFormat("dd MMMM yyyy")
var DMYDateFormat = SimpleDateFormat("dd-MM-yyyy")

var cutDatesList: MutableList<CutDate> = mutableListOf(
    "31-12-2022",
    "31-12-2023",
    "28-11-2024",
    "19-04-2025"
).map { date -> DMYDateFormat.parse(date)!! }
    .map { date -> CutDate(allCuts.toList().random().second.id, date) } as MutableList<CutDate>

var caresList = listOf(
    "29-12-2022",
    "24-12-2023",
    "15-11-2024",
    "13-12-2025"
).map { date -> DMYDateFormat.parse(date)!! }


val ageGroupList = listOf(
    R.string.age_0_12,
    R.string.age_13_17,
    R.string.age_18_25,
    R.string.age_26_35,
    R.string.age_36_45,
    R.string.age_46_,
)

val cutFrequencyList = listOf(
    R.string.weekly,
    R.string.evere_two_weeks,
    R.string.monthly,
    R.string.more_rarely
)

val headFormList = listOf(
    R.string.circle,
    R.string.oval,
    R.string.square,
    R.string.heart,
    R.string.triangle,
    R.string.diamond,
    R.string.oblong
)

val hairStructList = listOf(
    R.string.straight,
    R.string.wavy,
    R.string.curly,
    R.string.spiral
)

val hairThicknessList = listOf(
    R.string.low,
    R.string.medium,
    R.string.high
)

val hairLenList = listOf(
    R.string.short_len,
    R.string.medium,
    R.string.long_len
)

val scalpTypeList = listOf(
    R.string.dry,
    R.string.normal,
    R.string.oily,
    R.string.sensitive
)

val hairDamageLevelList = listOf(
    R.string.healthy,
    R.string.slightly_damaged,
    R.string.severely_damaged
)

val chemicalProceduresList = listOf(
    R.string.coloring,
    R.string.highlighting,
    R.string.straightening,
    R.string.none
)

val washingFrequencyList = listOf(
    R.string.daily,
    R.string.every_other_day,
    R.string.two_to_three_times_a_week,
    R.string.less_frequently
)

val averageStylingTimeList = listOf(
    R.string.blonde,
    R.string.brunette,
    R.string.black_haired,
    R.string.ginger
)

val stylingToolsList = listOf(
    R.string.hair_dryer,
    R.string.flat_iron,
    R.string.curling_iron,
    R.string.brush
)

val lifestyleList = listOf(
    R.string.sporty,
    R.string.business,
    R.string.calm,
    R.string.creative
)

val temperamentStyleList = listOf(
    R.string.experimentalist,
    R.string.minimalist,
    R.string.conservative
)

val hairColorList = listOf(
    R.string.blonde,
    R.string.brunette,
    R.string.black_haired,
    R.string.ginger
)

val bodyTypeList = listOf(
    R.string.thin,
    R.string.average,
    R.string.sporty,
    R.string.plump
)

val paramNameList = listOf(
    R.string.client_age_group,
    R.string.cut_frequency,
    R.string.cut_head_form,
    R.string.cut_hair_struct,
    R.string.cut_hair_thickness,
    R.string.cut_hair_len,
    R.string.cut_scalp_type
)

val questionList = listOf(
    R.string.question_age_group,
    R.string.question_cut_frequency,
    R.string.question_head_form,
    R.string.question_hair_struct,
    R.string.question_hair_thickness,
    R.string.question_hair_len,
    R.string.question_scalp_type
)

val paramsList = listOf(
    ageGroupList,
    cutFrequencyList,
    headFormList,
    hairStructList,
    hairThicknessList,
    hairLenList,
    scalpTypeList
)