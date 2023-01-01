package app.viewex.app.details

import app.viewex.app.AppDefinition
import app.viewex.core.details.IdentifiedPath
import app.viewex.core.details.ObjectName
import app.viewex.core.exception.AnonymousClassException
import kotlin.reflect.KClass

class AppName(name: String) : IdentifiedPath.Item(name), ObjectName {

    companion object {

        fun nameOfClass(appClass: KClass<out AppDefinition<*>>) : AppName =
            appClass.simpleName?.let {
                AppName(it.removeSuffix("App"))
            } ?: throw AnonymousClassException(appClass::class)

    }
}
