package app.viewex.domain

import kotlin.reflect.KClass

abstract class DomainException(message: String) : Exception(message) {

    class EmptyEntityId(entityClass: KClass<out Entity<*>>) : DomainException(
        "Entity [ ${entityClass.qualifiedName} ] has empty id value"
    )

}
