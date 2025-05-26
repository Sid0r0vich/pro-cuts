package com.sidor.procuts.network

import kotlinx.serialization.Serializable

@Serializable
data class Features(
    val Возраст: String,
    val Форма_Лица: String,
    val Структура_волос: String,
    val Густота_волос: String,
    val Длина_волос: String,
    val Цвет_волос: String,
    val Среднее_время: String,
    val Образ_жизни: String,
    val Стиль: String,
    val Телосложение: String,
    val Стиль_одежды: String,
    val Использование_средств: String,
    val Тип_укладки: String,
    val Использование_фена: String
)

val defaultFeatures =
    Features(
        Возраст = "30",
        Форма_Лица = "Овальная",
        Структура_волос = "Кудрявые",
        Густота_волос = "Высокая",
        Длина_волос = "Длинные",
        Цвет_волос = "Брюнет",
        Среднее_время = "20",
        Образ_жизни = "Активный",
        Стиль = "Классика",
        Телосложение = "Стройное",
        Стиль_одежды = "Элегантный",
        Использование_средств = "Да",
        Тип_укладки = "Объемная",
        Использование_фена = "Да"
    )