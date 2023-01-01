package app.viewex.domain.criteria

import app.viewex.core.type.ValueType

sealed interface ListCriteria<Val : Any> : CriteriaField {

    val type: Type

    val value: Val

    abstract class Single<Val : ValueType<*>> : ListCriteria<Val> {

        final override val type: Type = Type.SINGLE

        abstract val comparator: Comparator.SingleValue
    }

    abstract class  Multiply<Val : ValueType<*>> : ListCriteria<List<Val>> {

        final override val type: Type = Type.MULTIPLY

        abstract val comparator: Comparator.MultiplyValue
    }

    enum class Combinator {
        INIT, AND, OR
    }

    enum class Type {
        SINGLE, MULTIPLY
    }
}
