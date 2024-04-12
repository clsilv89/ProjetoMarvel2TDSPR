package com.caiosilva.projetomarvel2tdspr

import android.widget.ImageView
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso

fun Any.toJsonString(): String {
    val gson = GsonBuilder().create()
    return gson.toJson(this)
}

fun <T> String.fromJson(clazz: Class<T>): T {
    val gson = GsonBuilder().create()
    return gson.fromJson(this, clazz)
}

fun ImageView.setImageFromUrl(url:String) {
    Picasso.get().load(url).into(this)
}