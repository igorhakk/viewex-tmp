package app.viewex.domain.criteria

import app.viewex.core.type.StringType

sealed class Comparator(
    name: String
) : StringType(name, 2, 20, "[A-Z\\_]*") {

    abstract class SingleValue(name: String) : Comparator(name) {

        object Equal : SingleValue("EQ")

        object NotEqual : SingleValue("NEQ")
    }

    abstract class MultiplyValue(name: String) : Comparator(name) {

        object In : MultiplyValue("IN")

        object NotIn : MultiplyValue("NOT_IN")
    }
}
