package app.viewex.composer

interface ViewDefinition<Props : View.Props> {

    fun buildProps(): Props

    interface ContentAware<Content : View.Content> {

        fun getContent(): Content
    }
}
