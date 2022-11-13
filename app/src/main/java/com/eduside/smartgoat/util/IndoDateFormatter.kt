package com.eduside.smartgoat.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

/**
 * Formatter untuk tanggal Indonesia Helper
 *
 * Object helper untuk memudahkan format tanggal ke format Indonesia
 */
var indoDayName =
    arrayOf("Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu", "Minggu")

var indoMonthName = arrayOf(
    "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli",
    "Agustus", "September", "Oktober", "November", "Desember"
)

var indoMonthShortName = arrayOf(
    "Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul",
    "Agu", "Sep", "Okt", "Nov", "Des"
)

// Menampilkan jam dari object DateTime
fun jam(dateTime: DateTime): String {
    val timeString = dateTime.toString(DATETIME_FORMAT)
    return timeString.substring(11, 16)
}

// Menampilkan jam dari string dateTime
fun jam(dateTimeString: String): String {
    val dateTime = DateTime.parse(
        dateTimeString,
        DateTimeFormat.forPattern(DATETIME_FORMAT)
    )
    val timeString = dateTime.toString(DATETIME_FORMAT)
    return timeString.substring(11, 16)
}

@BindingAdapter("app:formatStringToJam")
fun jam(view: TextView, dateTimeString: String) {
    val dateTime =
        DateTime.parse(dateTimeString, DateTimeFormat.forPattern(DATETIME_FORMAT))
    val timeString = dateTime.toString(DATETIME_FORMAT)
    view.text = timeString.substring(11, 16)
}

// Menampilkan nama hari dari object DateTime
fun hari(dateTime: DateTime): String {
    return indoDayName[dateTime.dayOfWeek - 1]
}

// Menampilkan tanggal berformat (hari<koma><spasi>tanggal<spasi>nama bulan<spasi>tahun) dari object DateTime
// contoh hasil format Senin, 01 Januari 2019
fun hariDanTanggal(dateTime: DateTime): String {
    val dateString = dateTime.toString("dd")
    val monthName = indoMonthName[dateTime.monthOfYear - 1]
    val yearString = dateTime.toString("yyyy")
    return indoDayName[dateTime.dayOfWeek - 1] + ", " + dateString + " " + monthName + " " + yearString
}

// Menampilkan tanggal berformat (hari<koma><spasi>tanggal<spasi>nama bulan<spasi>tahun) dari string dateTime
// contoh hasil format Senin, 01 Januari 2019
fun hariDanTanggal(dateTimeString: String, lang: String = "ID"): String {
    val dateTime = DateTime.parse(
        dateTimeString,
        DateTimeFormat.forPattern(DATETIME_FORMAT)
    )
    if (lang == "EN") {
        indoDayName = arrayOf(
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"
        )
        indoMonthName = arrayOf(
            "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"
        )
    } else {
        indoDayName = arrayOf("Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu", "Minggu")
        indoMonthName = arrayOf(
            "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli",
            "Agustus", "September", "Oktober", "November", "Desember"
        )
        indoMonthShortName = arrayOf(
            "Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul",
            "Agu", "Sep", "Okt", "Nov", "Des"
        )
    }
    val dateString = dateTime.toString("dd")
    val monthName = indoMonthName[dateTime.monthOfYear - 1]
    val yearString = dateTime.toString("yyyy")
    return indoDayName[dateTime.dayOfWeek - 1] + ", " + dateString + " " + monthName + " " + yearString
}

@BindingAdapter("app:formatStringToHariDanTanggal")
fun hariDanTanggal(view: TextView, dateTimeString: String) {
    val dateTime =
        DateTime.parse(dateTimeString, DateTimeFormat.forPattern(DATETIME_FORMAT))
    val dateString = dateTime.toString("dd")
    val monthName = indoMonthName[dateTime.monthOfYear - 1]
    val yearString = dateTime.toString("yyyy")
    val text =
        indoDayName[dateTime.dayOfWeek - 1] + ", " + dateString + " " + monthName + " " + yearString
    view.text = text
}

@BindingAdapter("app:formatStringToTanggalDanJam")
fun formatStringToTanggalDanJam(view: TextView, dateTimeString: String?) {
    if (dateTimeString.isNullOrBlank()) {
        view.text = "-"
    } else {
        val dateTime =
            DateTime.parse(dateTimeString, DateTimeFormat.forPattern(DATETIME_FORMAT))
        val timeString = dateTime.toString(TIME_FORMAT)
        val dateString = dateTime.toString("dd")
        val monthName = indoMonthShortName[dateTime.monthOfYear - 1]
        val yearString = dateTime.toString("yyyy")
        val formattedDateString = "$dateString $monthName $yearString $timeString"
        view.text = formattedDateString
    }
}

@BindingAdapter("app:formatTanggalDanJam")
fun formatTanggalDanJam(view: TextView, dateTime: DateTime?) {
    if (dateTime == null) {
        view.text = "-"
    } else {
        val timeString = dateTime.toString(TIME_FORMAT)
        val dateString = dateTime.toString("dd")
        val monthName = indoMonthShortName[dateTime.monthOfYear - 1]
        val yearString = dateTime.toString("yyyy")
        val formattedDateString = "$dateString $monthName $yearString $timeString"
        view.text = formattedDateString
    }
}

fun formatTanggalDanJam(dateTime: DateTime?): String {
    return if (dateTime == null) {
        ""
    } else {
        val timeString = dateTime.toString(TIME_FORMAT)
        val dateString = dateTime.toString("dd")
        val monthName = indoMonthShortName[dateTime.monthOfYear - 1]
        val yearString = dateTime.toString("yyyy")
        "$dateString $monthName $yearString $timeString"
    }
}

fun formatStringToTanggalDanJam(
    dateTimeString: String?,
    pattern: String = DATETIME_FORMAT
): String {
    return if (dateTimeString.isNullOrBlank()) {
        ""
    } else {
        val dateTime =
            DateTime.parse(dateTimeString, DateTimeFormat.forPattern(pattern))
        val timeString = dateTime.toString(TIME_FORMAT)
        val dateString = dateTime.toString("dd")
        val monthName = indoMonthShortName[dateTime.monthOfYear - 1]
        val yearString = dateTime.toString("yyyy")
        "$dateString $monthName $yearString $timeString"
    }
}

fun formatStringToTanggal(
    dateTimeString: String?,
    pattern: String = DATETIME_FORMAT,
    useShortMonthName: Boolean = false
): String {
    return if (dateTimeString.isNullOrBlank()) {
        ""
    } else {
        val dateTime =
            DateTime.parse(dateTimeString, DateTimeFormat.forPattern(pattern))
        val dateString = dateTime.toString("dd")
        val monthName =
            if (useShortMonthName) indoMonthShortName[dateTime.monthOfYear - 1] else indoMonthName[dateTime.monthOfYear - 1]
        val yearString = dateTime.toString("yyyy")
        "$dateString $monthName $yearString"
    }
}

fun durasi(menit: Int): String {
    val res = StringBuilder()
    val day = menit / 1440
    val rem = menit % 1440
    val hour = rem / 60
    val min = rem % 60
    when {
        day > 0 -> {
            res.append("$day hari")
            if (min > 0) {
                res.append(" $hour jam $min menit")
            } else {
                res.append(" $hour jam")
            }
        }
        hour > 0 -> {
            res.append("$hour jam")
            if (min > 0) {
                res.append(" $min menit")
            }
        }
        else -> {
            res.append("$min menit")
        }
    }
    return res.toString()
}