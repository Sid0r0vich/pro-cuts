package com.sidor.procuts.network.googleforms

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Option(
    @SerialName("value")
    val value: String
)

@Serializable
data class ChoiceQuestion(
    @SerialName("type")
    val type: String,
    @SerialName("options")
    val options: List<Option>
)

@Serializable
data class Question(
    @SerialName("questionId")
    val questionId: String,
    @SerialName("choiceQuestion")
    val choiceQuestion: ChoiceQuestion
)

@Serializable
data class QuestionItem(
    @SerialName("question")
    val question: Question
)

@Serializable
data class Item(
    @SerialName("itemId")
    val itemId: String,
    @SerialName("title")
    val title: String,
    @SerialName("questionItem")
    val questionItem: QuestionItem
)

@Serializable
class FormResponse(
    @SerialName("formId")
    val formId: String,
    @SerialName("items")
    val items: List<Item>
) {
    fun toQuestionList(): List<QuestionOptions> =
        items.map { item ->
            QuestionOptions(
                question = item.title,
                options = item.questionItem.question.choiceQuestion.options.map { option -> option.value }
            )
        }
}