package app.viewex.app.context

import app.viewex.app.security.Principal

interface AppContextFactory<P : Principal<*>> {
    fun createContext(session: UiSession<P>) : AppContext<P>
}
