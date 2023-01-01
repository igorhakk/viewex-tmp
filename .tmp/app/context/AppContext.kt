package app.viewex.app.context

import app.viewex.app.security.Principal
import app.viewex.view.ViewContext

interface AppContext<P : Principal<*>> : ViewContext {
    val principal: P
}
