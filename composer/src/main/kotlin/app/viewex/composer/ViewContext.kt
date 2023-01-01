package app.viewex.composer

import app.viewex.composer.action.ViewAction
import app.viewex.composer.event.EventListener

interface ViewContext {

    val sessionInfo: SessionInfo

    fun registerListener(listener: EventListener)

    fun callAction(action: ViewAction)

}
