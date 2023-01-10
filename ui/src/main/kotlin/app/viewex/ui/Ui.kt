package app.viewex.ui

import app.viewex.app.App
import app.viewex.app.AppLayout
import app.viewex.composer.DynamicLayout
import app.viewex.composer.Layout
import app.viewex.composer.View
import app.viewex.composer.ViewContext
import app.viewex.composer.layout.LayoutDetails
import app.viewex.core.secutity.Principal

abstract class Ui<PrincipalType : Principal<*, *>>(
    private val sessionContextMapper: SessionContextMapper = DefaultViewContext.Mapper
) : UiContext<PrincipalType>, Layout {

    private val layout: DynamicLayout? = null

    private val sessionMap: MutableMap<UiSessionId, UiSession<*>> = mutableMapOf()

    private val appMap: MutableMap<String, AppLayout> = mutableMapOf()

    override val details: LayoutDetails
        get() = TODO("Not yet implemented")

    override val apps: Collection<AppLayout> get() = appMap.values.toSet()

    final override fun startSession(session: UiSession<PrincipalType>) {

        if (sessionMap.containsValue(session))
            throw IllegalStateException("Ui session [ ${session.id} ] already exist")

        sessionMap[session.id] = session

        val ctx = sessionContextMapper.map(session)

        val appName = ctx.sessionInfo.getRoute().findItem(0)

    }

    override fun closeSession(sessionId: UiSessionId) {

        if (!sessionMap.containsKey(sessionId))
            throw IllegalStateException("Ui session [ $sessionId ] not found")

        sessionMap.remove(sessionId)
    }

    protected fun <App : AppLayout> registerApp(app: App) : App {
        if (appMap.containsValue(app))
            throw IllegalStateException("App [ ${app.name} ] already exists")

        appMap[app.name.value] = app

        return app
    }


    protected abstract fun getIndexView(ctx: ViewContext): View

    protected abstract fun getNotFoundView(ctx: ViewContext): View
}
