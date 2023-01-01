package app.viewex.domain

import app.viewex.core.type.ValueType
import app.viewex.domain.criteria.ListQuery
import app.viewex.domain.event.DomainEventPublisher

interface Repository<Principal : app.viewex.core.secutity.Principal<*, *>> {

    val eventPublisher: DomainEventPublisher

    interface FullText<
            E : Entity,
            Text : ValueType.String,
            Principal : app.viewex.core.secutity.Principal<*, *>> : Repository<Principal> {

        suspend fun find(query: ListQuery.FullText<Text>): Collection<E>

    }

    interface ReadOnly<
            E : Entity.Identified<ID>,
            ID : EntityId<*>,
            Principal : app.viewex.core.secutity.Principal<*, *>> : Repository<Principal> {

        suspend fun find(query: ListQuery.Identified<ID>): Collection<E>

        suspend fun findById(id: ID) : E?
    }


    interface Modifiable<
            E : Entity.Identified<ID>,
            ID : EntityId<*>,
            Principal : app.viewex.core.secutity.Principal<*, *>> : IdGenerator<ID>, Repository<Principal> {

        suspend fun save(entity: E): ID

        suspend fun delete(query: ListQuery.Identified<ID>)

        suspend fun findById(id: ID) : E?
    }

    interface Crud<
            E : Entity.Identified<ID>,
            ID : EntityId<*>,
            Principal : app.viewex.core.secutity.Principal<*, *>
            > : ReadOnly<E, ID, Principal>, Modifiable<E, ID, Principal>

    interface IdGenerator<ID : EntityId<*>> {
        suspend fun getNextId(): ID
    }

}
