package app.viewex.domain

import app.viewex.core.details.IdentifiedPath
import app.viewex.core.details.ObjectName
import app.viewex.core.exception.AnonymousClassException
import app.viewex.core.util.firstToLower
import kotlin.reflect.KClass

class EntityName(name: String) : ObjectName, IdentifiedPath.Item(name) {

    companion object {
        fun ofClassName(
            entityClass: KClass<out Entity<*>>
        ): EntityName = entityClass.simpleName?.let {
            EntityName(it.removeSuffix("Entity").firstToLower())
        } ?: throw AnonymousClassException(Entity::class)
    }

}
