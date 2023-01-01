package app.viewex.app.action

import app.viewex.app.ui.ReceivedMessage
import app.viewex.view.ViewId
import app.viewex.view.tmp.event.EventData
import app.viewex.view.event.EventName
import app.viewex.view.tmp.event.ListenerContainer
import java.util.function.Supplier

class ViewMessageHandler(
    private val listenerContainer: Supplier<ListenerContainer>
) : MessageHandler {

    companion object {
        val Type = ReceivedMessage.Type("viewEvent")
    }

    override val type: ReceivedMessage.Type = Type

    override suspend fun handle(data: MessageData) {
        try {
            val eventName = EventName.parse(data["eventName"])
            val viewId = ViewId.parse(data["viewId"])
            val eventData = EventData.parse(data["eventData"])

            listenerContainer.get().getListeners(viewId).forEach {
                if (eventName == it.name) it.handle(eventData)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}
