package app.viewex.ui

import app.viewex.composer.SessionInfo
import app.viewex.core.secutity.Principal


interface UiSession<PrincipalType : Principal<*, *>> {

    val id: UiSessionId

    val sessionInfo: SessionInfo

    val principal: PrincipalType

    fun send(message: UiMessage.Direct)

    fun receive(message: UiMessage.Received)

    fun addReceiveMessageListener(listener: ReceiveMessageListener)
}
