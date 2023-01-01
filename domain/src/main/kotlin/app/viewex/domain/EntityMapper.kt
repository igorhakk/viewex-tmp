package app.viewex.domain

interface EntityMapper<E : Entity> {
    fun mapEntity(entityArgs: EntityArgs): E
    fun updateEntity(e: E, entityArgs: EntityArgs)
}
