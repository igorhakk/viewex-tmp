package app.viewex.domain

import app.viewex.core.type.ValueMapper
import kotlin.reflect.KClass

internal class EntityField(name: String, entityClass: KClass<out Entity<*>>) : Field {
    override val name: FieldName = FieldName.create(name, entityClass)

    class Mapped<Val : Any> private constructor(
        override val name: FieldName,
        override val mapper: ValueMapper<Val>
    ) : Field.Mapped<Val> {

        constructor(
            field: Field,
            mapper: ValueMapper<Val>
        ) : this(field.name, mapper)

        constructor(
            name: String,
            entityClass: KClass<out Entity<*>>,
            mapper: ValueMapper<Val>
        ) : this(FieldName.create(name, entityClass), mapper)

    }
}
