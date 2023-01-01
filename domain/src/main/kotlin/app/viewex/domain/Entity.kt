package app.viewex.domain

import app.viewex.core.util.ClassUtils

abstract class Entity {

    abstract class Identified<ID : EntityId<*>>(
        id: ID?
    ) : Entity() {

        private val _id: ID? = if (id == null || id.isEmpty) null else id

        val id: ID get() = _id ?: throw DomainException.EmptyEntityId(this::class)

        fun isNew(): Boolean = _id == null
    }

    override fun toString(): String = ClassUtils.memberPropsToString(this)
}
