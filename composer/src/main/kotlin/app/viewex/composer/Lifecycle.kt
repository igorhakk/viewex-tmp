package app.viewex.composer


import app.viewex.composer.event.EventHandler
import app.viewex.composer.event.EventListener
import app.viewex.composer.event.EventName


interface Lifecycle  {

    companion object {
        val OnAttachedViewEventName = EventName.Basic("onAttachedView")
        val OnDetachedViewEventName = EventName.Basic("onDetachedView")
    }

    fun addOnAttachedViewListener(handler: EventHandler.Simple): EventListener

    fun addOnDetachedViewListener(handler: EventHandler.Simple): EventListener
}
