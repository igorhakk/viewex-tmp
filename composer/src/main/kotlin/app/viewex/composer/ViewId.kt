package app.viewex.composer

import app.viewex.core.details.IdentifiedPath
import app.viewex.core.details.ObjectName
import app.viewex.core.util.toUuid
import java.util.*
import kotlin.reflect.KClass

class ViewId private constructor(uuid: UUID?) : ObjectName, IdentifiedPath.Item(uuid) {

    companion object {

        val Empty = ViewId(null)

        fun generate(
            viewClass: KClass<out View>
        ) : ViewId = viewClass.qualifiedName?.toUuid().let {
            ViewId(it)
        }

        fun parse(uuid: Any): ViewId = ViewId(UUID.fromString(uuid.toString()))

        fun parseOrNull(uuid: Any?): ViewId? = uuid?.let { parse(it) }

    }
}
