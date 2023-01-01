package app.viewex.composer

import app.viewex.core.type.StringType

class TemplateName(
    name: String
) : StringType(name, 2, 30, Pattern) {

    companion object {
        const val Pattern: String = "[A-Z][A-Za-z]{1,29}"
    }

}
