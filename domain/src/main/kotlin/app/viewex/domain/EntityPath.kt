package app.viewex.domain

import app.viewex.core.details.IdentifiedPath

class EntityPath private constructor(
    items: Iterable<Item>
) : IdentifiedPath(items) {

    constructor(
        entityGroup: EntityGroup,
        entityName: EntityName
    ) : this(listOf(BaseItem, *entityGroup.items.toTypedArray(), entityName))

    companion object {
        val BaseItem = Item.parse("entity")
    }
}
