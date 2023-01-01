package app.viewex.domain

import app.viewex.core.type.ValueMapper
import kotlin.reflect.KClass

abstract class EntityDefinition<E : Entity>(
    final override val entityClass: KClass<E>,
    entityGroup: EntityGroup,
    entityName: String? = null
) : EntityMetadata<E>{


    final override val name: EntityName = entityName?.let {
        EntityName(it)
    } ?: EntityName.ofClassName(entityClass)

    final override val path: EntityPath = EntityPath(entityGroup, name)

    private val fieldMap: MutableMap<String, Field> = mutableMapOf()

    final override val fields: Collection<Field> get() = fieldMap.values.toList()


    protected fun registerField(
        name: String
    ): Field = EntityField(
        name,
        entityClass
    ).also { registerField(it) }


    protected fun <Val : Any> Field.mapped(
        mapper: ValueMapper<Val>
    ): Field.Mapped<Val> = EntityField.Mapped(
        this,
        mapper
    ).also { replaceField(this, it) }


    private fun registerField(field: Field) {
        val name = field.name.value
        if (fieldMap.containsKey(name))
            throw IllegalStateException("Field with name [ $name ] already exists")
        fieldMap[name] = field
    }

    private fun replaceField(oldField: Field, newField: Field) {
        if (!fieldMap.containsValue(oldField))
            throw IllegalStateException("Field with name [ ${oldField.name} ] not found")

        fieldMap.remove(oldField.name.value)
        registerField(newField)
    }

    abstract class Identified<E : Entity.Identified<ID>, ID : EntityId<*>>(
        entityClass: KClass<E>,
        entityGroup: EntityGroup,
        entityName: String? = null
    ) : EntityDefinition<E>(entityClass, entityGroup, entityName), EntityMetadata.Identified<E, ID> {

        final override val IdField: Field.Mapped<ID> =
            registerField("id").mapped(this.getIdMapper())

        protected abstract fun getIdMapper() : ValueMapper<ID>
    }
}
