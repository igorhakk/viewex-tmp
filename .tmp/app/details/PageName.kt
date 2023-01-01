package app.viewex.app.details

import app.viewex.app.PageDefinition
import app.viewex.core.details.IdentifiedPath
import app.viewex.core.details.ObjectName
import app.viewex.core.exception.AnonymousClassException
import kotlin.reflect.KClass

class PageName(name: String) : ObjectName, IdentifiedPath.Item(name) {

    companion object {

        fun nameOfClass(appClass: KClass<out PageDefinition>) : PageName =
            appClass.simpleName?.let {
                PageName(it.removeSuffix("Page"))
            } ?: throw AnonymousClassException(appClass::class)

    }
}
