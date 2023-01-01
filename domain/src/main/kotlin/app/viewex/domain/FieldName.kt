package app.viewex.domain

import app.viewex.core.details.IdentifiedPath
import app.viewex.core.details.ObjectName
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties

class FieldName private constructor(
    name: String
) : ObjectName, IdentifiedPath.Item(name) {

    companion object {
        fun create(name: String, entityClass: KClass<out Entity<*>>): FieldName {
            if (entityClass.declaredMemberProperties.none { it.name == name })
                throw IllegalArgumentException(
                    "Parameter [ $name ] for the entity class [ ${entityClass.qualifiedName}] not found"
                )
            return FieldName(name)
        }
    }
}
