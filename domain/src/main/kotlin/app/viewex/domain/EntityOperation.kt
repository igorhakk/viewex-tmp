package app.viewex.domain

import app.viewex.core.type.ValueType
import app.viewex.domain.criteria.ListQuery

interface EntityOperation {

    interface ReadOnly<E : Entity.Identified<ID>, ID : EntityId<*>> : EntityOperation {

        suspend fun <Principal : app.viewex.core.secutity.Principal<*, *>> find(
            listQuery: ListQuery.Identified<ID>,
            repository: Repository.ReadOnly<E, ID, Principal>,
            principal: Principal
        ): Collection<E>

        suspend fun <Principal : app.viewex.core.secutity.Principal<*, *>> findById(
            id: ID,
            repository: Repository.ReadOnly<E, ID, Principal>,
            principal: Principal
        ): E

        suspend fun <Principal : app.viewex.core.secutity.Principal<*, *>> exists(
            id: ID,
            repository: Repository.ReadOnly<E, ID, Principal>,
            principal: Principal
        ): Boolean
    }

    interface Modify<E : Entity.Identified<ID>, ID : EntityId<*>> : EntityOperation {

        suspend fun <Principal : app.viewex.core.secutity.Principal<*, *>> create(
            entity: E,
            repository: Repository.Modifiable<E, ID, Principal>,
            principal: Principal
        ): ID

        suspend fun <Principal : app.viewex.core.secutity.Principal<*, *>> update(
            entity: E,
            repository: Repository.Modifiable<E, ID, Principal>,
            principal: Principal
        )

        suspend fun <Principal : app.viewex.core.secutity.Principal<*, *>> deleteById(
            id: ID,
            repository: Repository.Modifiable<E, ID, Principal>,
            principal: Principal
        )

        suspend fun <Principal : app.viewex.core.secutity.Principal<*, *>> delete(
            query: ListQuery.Identified<ID>,
            repository: Repository.Modifiable<E, ID, Principal>,
            principal: Principal
        )
    }

    interface FullTextQuery<Query : ValueType.String, E : Entity> : EntityOperation {

        suspend fun <Principal : app.viewex.core.secutity.Principal<*, *>> find(
            listQuery: ListQuery.FullText<Query>,
            repository: Repository.FullText<E, Query, Principal>,
            principal: Principal
        ): Collection<E>

    }
}
