package com.example.mob21firabase.core.utils

import com.example.mob21firabase.data.model.ValidationField

object ValidationUtil {
    fun validate(vararg fields: ValidationField): String? {
        fields.forEach { field ->
            if(!Regex(field.regExo).matches(field.value)) {
                return field.errMsg
            }
        }
        return null
    }
}