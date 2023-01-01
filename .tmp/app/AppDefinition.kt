package app.viewex.app

import app.viewex.app.context.AppContextFactory
import app.viewex.app.details.AppDetails
import app.viewex.app.details.AppName
import app.viewex.app.details.AppPath
import app.viewex.app.security.Principal
import app.viewex.core.details.ObjectDefinition

interface AppDefinition<P : Principal<*>> : ObjectDefinition<AppName, AppPath, AppDetails>, AppContextFactory<P> {
}
