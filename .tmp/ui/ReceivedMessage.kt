package app.viewex.app.ui

class ReceivedMessage(
    val type: Type,
    val data: MessageData
) {
    class Type(name: String) : MessageType(name)
}

fun <Res> ReceivedMessage.mapData(mapper: MessageDataMapper<Res>): Res = mapper.map(this.data)
