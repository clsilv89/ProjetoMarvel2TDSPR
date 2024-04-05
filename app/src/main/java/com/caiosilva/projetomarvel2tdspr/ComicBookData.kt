package com.caiosilva.projetomarvel2tdspr

data class ComicBookData(
    val id: Int,
    val title: String,
    val description: String,
    val pageCount: Int,
    val imageUrl: String,
)