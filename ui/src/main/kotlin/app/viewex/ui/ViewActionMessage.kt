package app.viewex.ui

import app.viewex.composer.action.ViewAction

class ViewActionMessage(
    action: ViewAction
) : UiMessage.Direct(MessageType, action) {

    companion object {
        val MessageType = UiMessage.Type("viewAction")
    }

    override val data: ViewAction = action
}
