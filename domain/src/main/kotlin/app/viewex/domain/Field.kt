package app.viewex.domain

import app.viewex.core.details.Named
import app.viewex.core.type.ValueMapper

interface Field : Named<FieldName> {

    interface Mapped<Val : Any> : Field {
        val mapper: ValueMapper<Val>
    }

}
