package app.viewex.app

import app.viewex.app.endpoint.Endpoint
import app.viewex.composer.View
import app.viewex.composer.ViewContext
import app.viewex.composer.layout.LayoutMetadata

interface AppContext : LayoutMetadata.Routed {

    val endpoints: Collection<Endpoint>

    fun buildView(ctx: ViewContext) : View

}
