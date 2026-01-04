package dodd.terran.util

import java.util.*

class ListMap<K, V>(list: List<Pair<K, V>>) {

    private val list = mutableListOf<Pair<K, V>>()
    private val map = mutableMapOf<K, Pair<Int, V>>()

    init {
        for ((key, value) in list) {
            this[key] = value
        }
    }

    constructor(vararg pairs: Pair<K, V>) : this(mutableListOf(*pairs))

    val size get() = list.size

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

    override fun hashCode() = Objects.hash(list, map)

    override fun equals(other: Any?) = other is ListMap<*, *> && list == other.list && map == other.map

    override fun toString() = list.toString()
}
