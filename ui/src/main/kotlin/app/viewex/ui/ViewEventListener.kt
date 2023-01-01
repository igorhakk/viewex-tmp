package app.viewex.ui

import app.viewex.composer.Lifecycle
import app.viewex.composer.event.EventData
import app.viewex.composer.event.EventName

class ViewEventListener(
    private val listenerSet: ListenerSet
) : ReceiveMessageListener {

    companion object {
        val MessageType = UiMessage.Type("viewEvent")
    }

    override val messageType: UiMessage.Type = MessageType

    override fun handle(data: UiMessage.DataParams) {
        val eventName = data[EventName.ParamName]?.let {
            EventName.parse(it)
        } ?: throw IllegalArgumentException("Illegal event name [ ${data[EventName.ParamName]} ] in ui message")

        if (isDetachEvent(eventName)) removeListeners(eventName)

        val eventData = EventData.parse(data[EventData.ParamName])

        listenerSet
            .filter {
                it.name == eventName
            }.forEach {
                it.handle(eventData)
            }
    }

    private fun isDetachEvent(
        eventName: EventName
    ): Boolean = eventName is EventName.Identified &&
            eventName.basicEvent == Lifecycle.OnDetachedViewEventName


    private fun removeListeners(detachedEvent: EventName) {
        listenerSet.toList().filter {

            val eventName = it.name

            if (eventName is EventName.Identified && detachedEvent is EventName.Identified)
                return@filter detachedEvent.viewId == eventName.viewId

            false
        }.forEach { listenerSet.remove(it) }
    }

}
