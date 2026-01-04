package dodd.terran

import dodd.terran.util.child
import dodd.terran.util.splitByWhitespace
import java.io.File

class Registry(dir: File) {

    private val internal = mutableMapOf<String, Map<String, Int>>()

    init {
        for (child in dir.child("Strings/GameDefines").listFiles()!!) {
            if (child.isFile) {
                val nameSplit = child.name.split("_GameStrings.h")
                if (nameSplit.size == 2 && nameSplit[1].isEmpty()) {
                    val inner = mutableMapOf<String, Int>()
                    for (line in child.readLines()) {
                        val trim = line.trim()
                        if (trim.isNotEmpty() && !trim.startsWith("//")) {
                            val lineSplit = trim.splitByWhitespace()
                            inner[lineSplit[1]] = lineSplit[2].toInt()
                        }
                    }
                    internal[nameSplit[0]] = inner
                }
            }
        }
    }

    operator fun get(key: String): Pair<String, Int?> {
        val category = key.split('_')[1]
        return category to internal[category]?.get(key)
    }
}
