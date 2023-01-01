package app.viewex.composer

import app.viewex.core.exception.AnonymousClassException
import app.viewex.core.type.StringType
import kotlin.reflect.KClass

interface View : IdentifiedView {

    val props: Props

    val content: Content

    val template: Template

    class Template(
        name: String
    ) : StringType(name, 2, 30, Pattern) {

        companion object {
            const val Pattern: String = "[A-Z][A-Za-z]{1,29}"

            fun ofClassName(
                viewClass: KClass<out View>
            ): Template = viewClass.simpleName?.let { className ->
                if (className.endsWith("View"))
                    return@let Template(className)
                return@let Template(className + "View")
            } ?: throw AnonymousClassException(View::class)
        }
    }

    interface Props {
        object Empty : Props {}
    }

    interface Content {
        object Empty : Content
    }
}
