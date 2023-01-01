package app.viewex.app

import app.viewex.app.context.AppContext
import app.viewex.app.context.DefaultAppContext
import app.viewex.app.details.AppDetails
import app.viewex.app.details.AppName
import app.viewex.app.details.AppPath
import app.viewex.app.security.Principal

abstract class App<P : Principal<*>>(
    name: String? = null
) : AppDefinition<P> {

    final override val name: AppName = name?.let { AppName(it) } ?: AppName.nameOfClass(this::class)

    override val path: AppPath = AppPath(this.name)

    final override val details: AppDetails = AppDetails.Configurer(this@App.name).also {
        configureDetails(it)
    }.getDetails()

    override fun createContext(
        session: UiSession<P>
    ): AppContext<P> = DefaultAppContext(session)

    protected abstract fun configureDetails(configurer: AppDetails.Configurer)
}
