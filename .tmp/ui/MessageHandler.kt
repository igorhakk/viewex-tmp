package app.viewex.app.ui

interface MessageHandler {

    val type: ReceivedMessage.Type

    suspend fun handle(data: MessageData)
}
