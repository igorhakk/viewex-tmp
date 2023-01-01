package app.viewex.app.ui

import app.viewex.core.type.StringType

abstract class MessageType(name: String) : StringType(name, 3,50, Pattern) {

    companion object {
        const val Pattern = "[a-z][A-Za-z]*"
    }
}
