package app.viewex.domain.event

import app.viewex.domain.Entity
import app.viewex.domain.EntityId
import app.viewex.domain.EntityPath
import app.viewex.domain.Principal
import java.time.LocalDateTime
import kotlin.reflect.KClass

interface DomainEvent {

    val occurredOn: LocalDateTime get() = LocalDateTime.now()

    interface EntityOperation : DomainEvent {
        val principal: Principal
        val entityPath: EntityPath
        val entityClass: KClass<out Entity>

        class Updated(
            override val principal: Principal,
            override val entityPath: EntityPath,
            override val entityClass: KClass<out Entity>,
            val id: EntityId<*>
        ) : EntityOperation

        class Created(
            override val principal: Principal,
            override val entityPath: EntityPath,
            override val entityClass: KClass<out Entity>,
            val id: EntityId<*>
        ) : EntityOperation

        class Deleted(
            override val principal: Principal,
            override val entityPath: EntityPath,
            override val entityClass: KClass<out Entity>,
            val id: EntityId<*>
        ) : EntityOperation

        class BeforeDeleteAll(
            override val principal: Principal,
            override val entityPath: EntityPath,
            override val entityClass: KClass<out Entity>,
            val idList: Collection<EntityId<*>>
        ) : EntityOperation
    }
}
