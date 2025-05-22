package com.sidor.procuts.utils

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber

object PhoneNumberParser {
    val phoneUtil = PhoneNumberUtil.getInstance()

    fun parsePhoneNumber(bareNumber: String): Phonenumber.PhoneNumber? {
        return try {
            if (bareNumber.length == 10) phoneUtil.parse(bareNumber, "RU") else null
        } catch (e: NumberParseException) { null }
    }
}
