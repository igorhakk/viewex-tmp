package app.viewex.domain

import app.viewex.domain.criteria.ListQuery
import app.viewex.domain.event.DomainEvent
import kotlin.reflect.KClass

abstract class ModifiedEntity<E : Entity.Identified<ID>, ID : EntityId<*>>(
    entityClass: KClass<E>,
    entityGroup: EntityGroup,
    entityName: String? = null
) : EntityDefinition.Identified<E, ID>(
    entityClass,
    entityGroup,
    entityName
), EntityOperation.Modify<E, ID> {

    final override suspend fun create(
        entity: E,
        repository: Repository.Modifiable<E, ID>,
        principal: Principal
    ): ID {

        val id = repository.save(entity)

        repository.eventPublisher.publish(
            DomainEvent.EntityOperation.Updated(
                principal,
                this.path,
                this.entityClass,
                id
            )
        )

        return id
    }

    final override suspend fun update(
        entity: E,
        repository: Repository.Modifiable<E, ID>,
        principal: Principal
    ) {

        val current = repository.findById(entity.id)

        if (current != null) {

            repository.save(entity)

            repository.eventPublisher.publish(
                DomainEvent.EntityOperation.Updated(
                    principal,
                    this.path,
                    this.entityClass,
                    current.id
                )
            )
        }
    }

    final override suspend fun deleteById(
        id: ID,
        repository: Repository.Modifiable<E, ID>,
        principal: Principal
    ) {
        repository.eventPublisher.publish(
            DomainEvent.EntityOperation.Deleted(
                principal,
                this.path,
                this.entityClass,
                id
            )
        )
    }

    final override suspend fun delete(
        query: ListQuery.Identified<ID>,
        repository: Repository.Modifiable<E, ID>,
        principal: Principal
    ) {
        TODO("Not yet implemented")
    }
}
