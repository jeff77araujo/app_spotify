package com.app.spotifyclone.model

import com.app.spotifyclone.R
import com.google.gson.annotations.SerializedName

data class Category(

    @SerializedName("titulo") var title: String = "",
    @SerializedName("albuns") var albums: List<Album> = arrayListOf()
    // var albums: MutableList<Album> = ArrayList()
)

data class Album(
    @SerializedName("url_imagem") var album: String = "",
    @SerializedName("id") var id: Int = 0
    //var album: Int = 0
)

data class Categories(
    @SerializedName("categoria") val categories: List<Category>
)

data class Section(
    var section : Int,
    var nameSection: String
)

class SectionBuilder {
    var section: Int = 0
    var nameSection: String = ""

    fun build(): Section = Section(section, nameSection)
}

fun section(block: SectionBuilder.() -> Unit): Section = SectionBuilder().apply(block).build()

fun data(): MutableList<Section> = mutableListOf(
    section {
        section = R.drawable.secao1
        nameSection = "Podcasts"
    },
    section {
        section = R.drawable.secao2
        nameSection = "Lançamentos"
    },
    section {
        section = R.drawable.secao3
        nameSection = "Em casa"
    },
    section {
        section = R.drawable.secao4
        nameSection = "Paradas"
    },
    section {
        section = R.drawable.secao1
        nameSection = "Shows"
    },
    section {
        section = R.drawable.secao2
        nameSection = "Made for You"
    },
    section {
        section = R.drawable.secao3
        nameSection = "Brasil"
    },
    section {
        section = R.drawable.secao1
        nameSection = "Rock"
    }
)