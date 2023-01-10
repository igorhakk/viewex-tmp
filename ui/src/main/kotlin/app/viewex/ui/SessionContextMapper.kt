package app.viewex.ui

import app.viewex.composer.ViewContext

interface SessionContextMapper {
    fun map(session: UiSession<*>) : ViewContext
}
