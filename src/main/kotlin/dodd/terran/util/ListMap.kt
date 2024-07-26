package dodd.terran.util

class ListMap<K, V>(vararg pairs: Pair<K, V>) {

    private val list = mutableListOf<Pair<K, V>>()
    private val map = mutableMapOf<K, Pair<Int, V>>()

    val size get() = list.size

    init {
        pairs.forEachIndexed { index, pair ->
            list.add(pair)
            map[pair.first] = index to pair.second
        }
    }

    operator fun get(key: K) = map[key]?.second

    operator fun get(key: Int) = list[key].second

    operator fun set(key: K, value: V) {
        val previous = map[key]
        if (previous == null) {
            val index = size
            list.add(key to value)
            map[key] = index to value
        }
        else {
            val (index) = previous
            list[index] = key to value
            map[key] = index to value
        }
    }

    operator fun iterator() = list.iterator()

    fun asSequence() = list.asSequence()

    override fun toString() = list.toString()
}
