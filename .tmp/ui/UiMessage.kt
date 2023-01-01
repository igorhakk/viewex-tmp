package app.viewex.app.ui

class UiMessage<Data>(
    val type: Type,
    val data: Data
) {
    class Type(name: String) : MessageType(name)
}
