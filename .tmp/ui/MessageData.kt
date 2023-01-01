package app.viewex.app.ui

import app.viewex.core.attribute.AttributeAccessor
import app.viewex.core.attribute.Attributes

class MessageData(
    props: Iterable<Pair<String, Any?>>
) : AttributeAccessor {

    private val attributes = Attributes(props)

    override val names: Set<String> = attributes.names

    override fun get(name: String): Any? = attributes[name]

    override fun contains(name: String): Boolean = attributes.contains(name)
}
