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
    Client(firstName = "Client"),
)

val allCuts = mapOf(
    0 to Cut(0, "Classic", "Классическая модельная стрижка представляет собой длину волос около пяти сантиметров, при этом присутствуют в основном ровные линии. Такая стрижка выглядит сдержанно и аккуратно, отлично подходит деловым мужчинам и тем, которые не хотят чрезмерного внимания к своему внешнему виду.", R.drawable.classic),
    1 to Cut(1, "Undercut", "Для стрижки характерна почти одинаковая длина волос по всей поверхности головы. Классический варианта – это выбритые виски и немного удлиненные волосы на верхней части. Андеркат в таком исполнении подходит практически всем. Он придаст образу мужественности и делового стиля.", R.drawable.undercut),
    2 to Cut(2, "Curtains", "Мужская стрижка, предполагающая прямой центральный пробор и симметричные пряди по обеим сторонам. При этом длина стрижки, наличие или отсутствие челки, градуировка никаким образом не регламентированы — здесь у нас с вами полная свобода.", R.drawable.curtains),
    3 to Cut(3, "Bold", "Цель данной стрижки — полностью обнажить кожу головы, сбрив все волосы. Стрижка под 0 способствует созданию образа уверенного и сильного человека, своему обладателю придает максимум мужественности, помогает почувствовать себя увереннее и подчеркнуть индивидуальность.", R.drawable.bold)
)

var cutNamesToId = allCuts.entries.associate { (key, value) -> value.name to key }

val readableDMYDateFormat = SimpleDateFormat("dd MMMM yyyy")
var DMYDateFormat = SimpleDateFormat("dd-MM-yyyy")

var cutDatesList: MutableList<CutDate> = mutableListOf(
    "31-12-2022",
    "31-12-2023",
    "28-11-2024",
    "31-12-2025"
).map { date -> DMYDateFormat.parse(date)!! }
    .map { date -> CutDate(allCuts.toList().random().second.id, date) } as MutableList<CutDate>

var caresList = listOf(
    "29-12-2022",
    "24-12-2023",
    "15-11-2024",
    "13-12-2025"
).map { date -> DMYDateFormat.parse(date)!! }