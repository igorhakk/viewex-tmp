package app.viewex.app.ui

interface UiMessagePublisher {
    suspend fun publish(message: UiMessage<*>)
}
