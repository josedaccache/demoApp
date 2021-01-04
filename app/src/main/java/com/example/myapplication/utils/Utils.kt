package com.example.myapplication.utils

import android.content.Context
import android.text.format.DateUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.utils.GlobalVars.DATE_FORMAT_INPUT
import com.example.myapplication.utils.GlobalVars.DATE_FORMAT_OUTPUT_FEED
import com.example.myapplication.utils.GlobalVars.DATE_FORMAT_OUTPUT_TIME
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

val EMAIL_PATTERN: Pattern =
    Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+")
val PHONE_PATTERN: Pattern = Pattern.compile("^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}\$")

fun convertLongToDate(time: Long): String {
    val calendar = Calendar.getInstance(Locale.US)
    calendar.timeInMillis = (time * 1000)
    val format = SimpleDateFormat("EEEE MMM d, yyyy", Locale.US)
    return format.format(calendar.time)
}

fun convertLongToTime(time: Long): String {
    val calendar = Calendar.getInstance(Locale.US)
    calendar.timeInMillis = (time * 1000)
    val format = SimpleDateFormat("hh a", Locale.US)
    return format.format(calendar.time)
}

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(iconUrl: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_error)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(iconUrl)
        .into(this)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun isEmailValid(email: String?): Boolean {
    return email != null && EMAIL_PATTERN.matcher(email).matches()
}

fun isPhoneValid(phone: String?): Boolean {
    return phone != null && Patterns.PHONE.matcher(phone).matches()
}

fun isValidName(name: String?): Boolean {
    if (name == null || name == "") {
        return false
    }
    val chars = name.toCharArray()
    for (c in chars) {
        if (Character.isDigit(c) || "!#$%&'()*+,-./:;<=>?@[]^_`{|}".contains(Character.toString(c))) {
            return false
        }
    }
    return true
}

fun getNewsFormattedDate(time: String?): String {
    var formattedDate = ""
    val inputTime = SimpleDateFormat(DATE_FORMAT_INPUT, Locale.US)
    val sdf = SimpleDateFormat(DATE_FORMAT_OUTPUT_FEED, Locale.US)
    val outputTime = SimpleDateFormat(DATE_FORMAT_OUTPUT_TIME, Locale.US)
    val date = try {
        inputTime.parse(time)
    } catch (ex: Exception) {
        Calendar.getInstance().time
    }
    formattedDate = if (DateUtils.isToday(date.time)) {
        "Today, ${outputTime.format(date)}"
    } else {
        sdf.format(date)
    }
    return formattedDate
}
