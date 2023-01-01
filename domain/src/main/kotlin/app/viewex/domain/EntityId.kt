package app.viewex.domain

import app.viewex.core.type.IntType
import app.viewex.core.type.LongType
import app.viewex.core.type.UuidType
import app.viewex.core.type.ValueType
import java.util.*

interface EntityId<Val : Any> : ValueType<Val> {

    abstract class AsUuid(
        value: Any?,
    ) : EntityId<UUID>, UuidType(value)

    abstract class AsInt(
        positiveInt: Any?,
        maxVal: Int = Int.MAX_VALUE,
        includeZero: Boolean = false
    ) : EntityId<Int>, IntType(positiveInt, if (includeZero) 0 else 1, maxVal)

    abstract class AsLong(
        positiveLong: Any?,
        maxVal: Long = Long.MAX_VALUE,
        includeZero: Boolean = false
    ) : EntityId<Long>, LongType(positiveLong, if (includeZero) 0 else 1, maxVal)

}
