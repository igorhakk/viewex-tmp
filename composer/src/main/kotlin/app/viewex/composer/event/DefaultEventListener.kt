package app.viewex.composer.event

open class DefaultEventListener(
    override val name: EventName,
    private val handler: EventHandler
) : EventListener {

    constructor(
        name: EventName,
        handler: EventHandler.Simple
    ) : this(name, EventHandler { handler.handle() })

    override fun handle(data: EventData) = handler.handle(data)
}
