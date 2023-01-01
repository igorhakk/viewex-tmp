package app.viewex.composer

import app.viewex.core.type.UrlPath

class Route(
    val path: UrlPath,
    val params: LayoutParams
) {

    fun findItem(
        index: Int
    ): String? = path.items.getOrNull(index)?.value

}
