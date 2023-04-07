package com.example.quicknote

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(
    @LayoutRes layoutId: Int,
): View = LayoutInflater.from(context)
    .inflate(layoutId, this, false)

@ColorInt
fun Context.getColorFromAttribute(@AttrRes attrId: Int): Int {
    return TypedValue().also {
        theme.resolveAttribute(attrId, it, true)
    }.data
}