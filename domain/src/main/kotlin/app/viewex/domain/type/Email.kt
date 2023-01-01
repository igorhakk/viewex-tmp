package app.viewex.domain.type

import app.viewex.core.type.StringType
import app.viewex.core.util.StringUtils


open class Email protected constructor(
    email: Any?
) : StringType(email, 6, 100, StringUtils.Pattern.Email) {

    companion object {
        val Empty = Email(null)

        fun parse(email: Any) : Email = Email(email)

        fun parseOrEmpty(email: Any?): Email = parseOrNull(email) ?: Empty

        fun parseOrNull(email: Any?): Email? = email?.let { Email(email) }

    }
}
