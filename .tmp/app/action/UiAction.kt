package app.viewex.app

import app.viewex.view.action.ViewAction

val ViewActionType = UiMessage.Type("viewAction")

fun <Action : ViewAction<*>> Action.toUiMessage() : UiMessage<Action> = UiMessage(ViewActionType, this)
