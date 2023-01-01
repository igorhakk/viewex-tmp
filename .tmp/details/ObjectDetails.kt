package app.viewex.app.details

import app.viewex.app.IdentifiedPath


interface ObjectDetails {

    val label: Label

    val description: Description

    val icon: IconName

    interface Named : ObjectDetails, app.viewex.app.Named<ObjectName>

    companion object {

        val LabelItem = IdentifiedPath.Item.parse("label")

        val DescriptionItem = IdentifiedPath.Item.parse("description")

    }
}
