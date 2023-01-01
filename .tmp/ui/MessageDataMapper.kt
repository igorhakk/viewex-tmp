package app.viewex.app.ui

interface MessageDataMapper<Res> {
    fun map(data: MessageData): Res
}
