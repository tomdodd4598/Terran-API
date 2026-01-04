package dodd.terran

import dodd.terran.util.child
import java.io.File

class Game(dir: String, lang: String) {

    companion object {
        private val gameDirChildren = sortedSetOf("Effects", "Fonts", "Movies", "Strings", "World", "WorldObjects")

        private val validLangs = sortedSetOf("English", "French", "German", "Italian", "Spanish")
    }

    private val dir: File
    private val locale: Locale
    private val registry: Registry

    init {
        this.dir = File(dir)
        if (!this.dir.run { isDirectory && list()!!.toSet().containsAll(gameDirChildren) }) {
            throw IllegalArgumentException("Game directory \"$dir\" is invalid!\nMust contain all of the following sub-directories: ${gameDirChildren.joinToString { "\"$it\"" }}.")
        }

        val capitalized = lang.capitalize()
        if (!validLangs.contains(capitalized)) {
            throw IllegalArgumentException("Game language \"$lang\" is invalid!\nMust select one of the following: ${validLangs.joinToString { "\"$it\"" }}.")
        }
        locale = Locale(this.dir, capitalized)

        registry = Registry(this.dir)
    }

    fun resource(name: String) = dir.child(name)

    fun localize(unlocalized: String): String {
        val (category, id) = registry[unlocalized]
        if (id == null) {
            throw IllegalArgumentException("Unlocalized name \"$unlocalized\" is not registered in associated category \"$category\"!")
        }
        return locale[unlocalized] ?: unlocalized
    }
}
