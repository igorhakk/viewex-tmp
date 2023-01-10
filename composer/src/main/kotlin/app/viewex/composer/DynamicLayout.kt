package app.viewex.composer

interface DynamicLayout : Layout {
    val content: DynamicView<*, *>
}
