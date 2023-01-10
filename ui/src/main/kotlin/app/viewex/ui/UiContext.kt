package app.viewex.ui

import app.viewex.app.AppLayout
import app.viewex.composer.Layout
import app.viewex.composer.layout.LayoutDetails
import app.viewex.core.secutity.Principal

interface UiContext<PrincipalType : Principal<*,*>> : Layout {

    val details: LayoutDetails

    val apps: Collection<AppLayout>

    fun startSession(session: UiSession<PrincipalType>)

    fun closeSession(sessionId: UiSessionId)

}
