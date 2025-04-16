package com.sidor.procuts.data

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

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
var dateFormat = SimpleDateFormat("dd-MM-yyyy")

var cutsList = listOf(
    "31-12-2022",
    "31-12-2023",
    "28-11-2024",
    "31-12-2025"
).map { date -> dateFormat.parse(date)!! }

var caresList = listOf(
    "29-12-2022",
    "24-12-2023",
    "15-11-2024",
    "13-12-2025"
).map { date -> dateFormat.parse(date)!! }