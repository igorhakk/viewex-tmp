package app.viewex.composer.event

open class MappedEventListener<T>(
    override val name: EventName,
    private val handler: EventHandler.Typed<T>,
    private val mapper: EventData.Mapper<T>,
    private val onMappingErrorCallback: (Exception) -> Unit = PrintErrorCallback(name)
) : EventListener {


    override fun handle(data: EventData) = try {
        val typedData = mapper.map(data)
        handler.handle(typedData)
    } catch (e: Exception) {
        onMappingErrorCallback(e)
    }

    class PrintErrorCallback(private val eventName: EventName) : (Exception) -> Unit {
        override fun invoke(e: Exception) {
            println("----------------------- VIEW EVENT DIDN'T HANDLE ----------------------------------------")
            println("Event [ $eventName] didn't handle, event data cannot be map")
            println("Exception [ ${e::class.qualifiedName} ] message [ ${e.message} ] ")
            println("-----------------------------------------------------------------------------------------")
        }

    }
}
