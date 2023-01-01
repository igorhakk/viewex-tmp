package app.viewex.ui

interface ReceiveMessageListener {
    val messageType: UiMessage.Type
    fun handle(data: UiMessage.DataParams)
}
