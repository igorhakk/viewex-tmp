package app.viewex.ui

import app.viewex.composer.SessionInfo
import app.viewex.composer.ViewContext
import app.viewex.composer.action.ViewAction
import app.viewex.composer.event.EventListener
import app.viewex.core.secutity.Principal


class DefaultViewContext<PrincipalType : Principal<*, *>>(
    private val session: UiSession<PrincipalType>
) : ViewContext {

    final override val sessionInfo: SessionInfo get() = session.sessionInfo

    val principal: PrincipalType = session.principal

    private val listenerSet = ListenerSet()

    init {
        session.addReceiveMessageListener(
            ViewEventListener(listenerSet)
        )
    }

    override fun registerListener(listener: EventListener) {

        listenerSet.add(listener)
    }

    override fun callAction(
        action: ViewAction
    ) = session.send(ViewActionMessage(action))
}
