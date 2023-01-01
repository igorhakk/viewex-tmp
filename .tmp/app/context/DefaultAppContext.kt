package app.viewex.app.context

import app.viewex.app.security.Principal
import app.viewex.app.toUiMessage
import app.viewex.view.action.ViewAction

class DefaultAppContext<P : Principal<*>>(
    private val uiSession: UiSession<P>
) : AppContext<P> {

    override val principal: P = uiSession.principal

    override suspend fun invokeAction(
        action: ViewAction<*>
    ) = uiSession.send(action.toUiMessage())

}
