package com.sidor.procuts.data

import com.sidor.procuts.R
import java.text.SimpleDateFormat

var defaultCliensList = listOf(
    "Jason Statham",
    "Dwayne Douglas Johnson",
    "Илья Игоревич Муромцев",
    "Дмитрий Сергеевич Шалымов",
    "Владимир Путин",
    "Евгений Туаев",
    "Роберт Смайт",
    "Павел Скаков",
    "Client",
    "Client",
    "Client"
)

val allCuts = mapOf(
    0 to Cut(0, "Classic", R.drawable.classic),
    1 to Cut(1, "Undercut", R.drawable.undercut),
    2 to Cut(2, "Shores", R.drawable.shores),
    3 to Cut(3, "Bold", R.drawable.bold)
)

var dateFormat = SimpleDateFormat("dd-MM-yyyy")

var cutDatesList = listOf(
    "31-12-2022",
    "31-12-2023",
    "28-11-2024",
    "31-12-2025"
).map { date -> dateFormat.parse(date)!! }
    .map { date -> CutDate(allCuts.toList().random().second.cutId, date) }

var caresList = listOf(
    "29-12-2022",
    "24-12-2023",
    "15-11-2024",
    "13-12-2025"
).map { date -> dateFormat.parse(date)!! }