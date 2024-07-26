package dodd.terran.translation

internal class FancyBuilder(private val internal: StringBuilder, private val indentation: String) {

    constructor() : this(StringBuilder(), "")

    fun inner() = FancyBuilder(internal, "\t$indentation")

    fun append(obj: Any, indent: Boolean) {
        if (indent) {
            internal.append(indentation)
        }
        internal.append(obj)
    }

    override fun toString() = internal.toString()
}
