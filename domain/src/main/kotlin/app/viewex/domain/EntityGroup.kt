package app.viewex.domain

import app.viewex.core.details.ObjectGroup

abstract class EntityGroup(
    items: Iterable<Item>
) : ObjectGroup(items) {
    constructor(vararg raw: String) : this(parseItems(*raw))
}
