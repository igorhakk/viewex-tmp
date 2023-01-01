package app.viewex.domain.criteria

import app.viewex.core.type.IntType
import app.viewex.core.type.LongType
import app.viewex.core.type.ValueType
import app.viewex.domain.EntityId
import app.viewex.domain.FieldName

interface ListQuery {

    val sortBy: Iterable<SortField>

    val limit: Limit

    val offset: Offset

    interface Identified<ID : EntityId<*>> : ListQuery {
        val idCriteria: ListCriteria.Multiply<ID>
    }

    interface FullText<Val : ValueType.String> : ListQuery {
        val fullTextCriteria: ListCriteria.Single<Val>
    }

    open class Limit protected constructor(
        limit: Any?,
        maxValue: Int = Int.MAX_VALUE
    ) : IntType(limit, 1, maxValue)

    class Offset private constructor(
        offset: Any?
    ) : LongType(offset, 0, Long.MAX_VALUE) {

        companion object {
            val Empty = Offset(null)

            fun parse(offset: Int): Offset = Offset(offset)

            fun parse(offset: Long): Offset = Offset(offset)

            fun parse(offset: String): Offset = Offset(offset)
        }
    }

    class SortField(
        val fieldName: FieldName,
        val direction: SortDirection = SortDirection.ASC
    )

    enum class SortDirection {
        ASC, DESC
    }
}
