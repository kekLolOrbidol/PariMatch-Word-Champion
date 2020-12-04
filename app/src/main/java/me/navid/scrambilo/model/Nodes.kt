package me.navid.scrambilo.model

class Nodes {
    val children = HashMap<Char, Nodes>()
    var isWord: Boolean = false

    var content: String? = null //Just for debugging
}