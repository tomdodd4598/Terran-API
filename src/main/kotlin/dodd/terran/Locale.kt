package dodd.terran

import dodd.terran.util.child
import java.io.File

class Locale(dir: File, lang: String) {

    private val internal = mutableMapOf<String, String>()

    init {
        val file = dir.child("Strings/${lang}_GameStrings.txt")
        val lineIter = file.readLines().iterator()

        fun nextLine() = lineIter.next().trim()

        while (lineIter.hasNext()) {
            val line = nextLine()
            if (line.isNotEmpty() && !line.startsWith("//")) {
                internal[line] = nextLine().removeSurrounding("\"", "\"")
            }
        }
    }

    operator fun get(key: String) = internal[key]
}
