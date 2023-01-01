package app.viewex.composer.layout.page

interface PageLayoutMap<T : PageLayout> : PageLayoutSet<T> {

    fun getPage(name: String) : T

    fun getPageOrNull(name: String) : T?

    fun contains(name: String) : Boolean
}
