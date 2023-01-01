package app.viewex.ui

import app.viewex.core.type.UrlPath
import app.viewex.core.util.ConvertUtils
import app.viewex.layout.event.*

interface RouteUpdatedListener {

    companion object {
        val OnRouteUpdatedEventName = EventName.Basic("onRouteUpdated")
    }

    fun addOnRouteUpdatedListener(
        handler: EventHandler.Typed<Route>
    ): EventListener

    object RouteDataMapper : EventData.Mapper<Route> {

        override fun map(data: EventData): Route {

            val path = data["path"]?.let {
                UrlPath.parse(it.toString())
            } ?: throw DataMappingException("Path value [ ${data["path"]} ] cannot be map to UrlPath")

            val params = ConvertUtils.toMap(data["params"])?.mapNotNull {
                if (it.key == null) return@mapNotNull null
                Pair(it.key.toString(), it.value)
            } ?: emptyList()

            return Route(path, Route.Params(params))
        }

    }

}
