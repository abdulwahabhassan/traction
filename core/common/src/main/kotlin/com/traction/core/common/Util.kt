package com.traction.core.common

import java.text.DecimalFormat

object Util {
    fun formatAmount(value: Any): String {
        val valueToBeFormatted: Number = if (value is String) {
            value.toDouble()
        } else {
            value as Number
        }

        val df = DecimalFormat("##,###,##0.00")
        return df.format(valueToBeFormatted)
    }
}