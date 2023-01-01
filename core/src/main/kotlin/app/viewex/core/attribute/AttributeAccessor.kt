package app.viewex.core.attribute

interface AttributeAccessor : IterablePair<String, Any?> {

    val names: Set<String>

    operator fun get(name: String): Any?

    operator fun contains(name: String): Boolean

    override fun iterator(): Iterator<Pair<String, Any?>> = names.map { Pair(it, get(it)) }.iterator()

    interface Mutable : AttributeAccessor {
        operator fun set(name: String, value: Any?)
    }
}

fun AttributeAccessor.toMap(): Map<String, Any?> = names.associateWith { get(it) }
