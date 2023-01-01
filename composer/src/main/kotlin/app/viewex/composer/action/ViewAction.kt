package app.viewex.composer.action

import app.viewex.composer.IdentifiedView
import app.viewex.composer.ViewId
import app.viewex.core.details.Named

class ViewAction(
    override val viewId: ViewId,
    override val name: ActionName,
    val data: Any
) : Named<ActionName>, IdentifiedView
