package app.viewex.app.ui

import app.viewex.app.security.Principal
import java.util.*

interface UiSession<P : Principal<*>> {

    val id: UiSessionId

    val principal: P

    val locale: Locale

    suspend fun send(message: UiMessage<*>)

    fun addMessageHandler(handler: MessageHandler)

}
