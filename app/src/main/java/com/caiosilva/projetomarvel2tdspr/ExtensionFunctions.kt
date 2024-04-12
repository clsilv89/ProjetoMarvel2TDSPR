package com.caiosilva.projetomarvel2tdspr

import com.google.gson.GsonBuilder

fun Any.toJsonString(): String {
    val gson = GsonBuilder().create()
    return gson.toJson(this)
}

fun <T> String.fromJson(clazz: Class<T>): T {
    val gson = GsonBuilder().create()
    return gson.fromJson(this, clazz)
}