package com.ltdt.coffeeshop_android_native.data.services

import java.time.LocalDateTime

interface DateTimeService {
    fun timeToString(time: LocalDateTime): String
    fun stringToTime(time: String): LocalDateTime
}