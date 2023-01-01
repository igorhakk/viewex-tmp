package app.viewex.domain

import app.viewex.core.details.Identified
import app.viewex.core.details.Named
import kotlin.reflect.KClass

interface EntityMetadata<E : Entity> : Named<EntityName>, Identified<EntityPath> {

    val entityClass: KClass<E>

    val fields: Collection<Field>

    interface Identified<E : Entity.Identified<ID>, ID : EntityId<*>> : EntityMetadata<E> {
        val IdField: Field.Mapped<ID>
    }


}
