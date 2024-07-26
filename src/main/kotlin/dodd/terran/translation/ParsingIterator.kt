package dodd.terran.translation

class ParsingIterator(private val internal: Iterator<String>) {

    fun hasNext() = internal.hasNext()

    fun tryNext(element: StackElement, start: Boolean) = element.tryNext(internal, start)
}
