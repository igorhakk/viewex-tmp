package app.viewex.domain.type

import app.viewex.core.type.StringType
import app.viewex.core.type.ValueType

interface PhoneNumber : ValueType.String {

    open class External protected constructor(
        number: Any?
    ) : PhoneNumber, StringType(number, 6,16, Pattern) {

        companion object {
            const val Pattern = "[12345679][0-9]*"

            val Empty = External(null)

            fun parse(number: Any) : External = External(number)
        }
    }
}
