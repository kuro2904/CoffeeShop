package com.ltdt.coffeeshop_android_native.data.services.impl

import com.ltdt.coffeeshop_android_native.data.services.DateTimeService
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeServiceImpl : DateTimeService {

    private val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")

    override fun timeToString(time: LocalDateTime): String {
        return formatter.format(time)
    }

    override fun stringToTime(time: String): LocalDateTime {
        return LocalDateTime.parse(time)
    }

}