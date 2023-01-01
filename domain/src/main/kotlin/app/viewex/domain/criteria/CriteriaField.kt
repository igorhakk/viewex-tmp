package app.viewex.domain.criteria

import app.viewex.domain.FieldName

interface CriteriaField {
    val fieldName: FieldName
    val combinator: ListCriteria.Combinator
}
