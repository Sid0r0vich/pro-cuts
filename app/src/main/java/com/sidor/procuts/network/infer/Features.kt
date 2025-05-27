package com.sidor.procuts.network.infer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Features(
    @SerialName("Возраст")
    val age: String,

    @SerialName("Форма Лица")
    val faceForm: String,

    @SerialName("Структура волос")
    val hairStruct: String,

    @SerialName("Густота волос")
    val hairDensity: String,

    @SerialName("Длина волос")
    val hairLen: String,

    @SerialName("Цвет волос")
    val hairColor: String,

    @SerialName("Среднее время на укладку(минут в день)")
    val averageStylingTime: String,

    @SerialName("Образ жизни")
    val lifestyle: String,

    @SerialName("Стиль")
    val style: String,

    @SerialName("Телосложение")
    val physique: String,

    @SerialName("Стиль одежды")
    val dressStyle: String,

    @SerialName("Использование укладочных средств")
    val isUsingStyling: String,

    @SerialName("Тип укладки")
    val styleType: String,

    @SerialName("Использование фена")
    val isUsingHairdryer: String
)

val defaultFeatures =
    Features(
        age = "19-28",
        faceForm = "Прямоугольная",
        hairStruct = "Прямые",
        hairDensity = "Средняя",
        hairLen = "Средние",
        hairColor = "Шатен",
        averageStylingTime = "1-10",
        lifestyle = "Деловой",
        style = "Смешанный",
        physique = "Спортивное",
        dressStyle = "Спортивный",
        isUsingStyling = "Да",
        styleType = "Неряшливо-растрепанная",
        isUsingHairdryer = "Да"
    )