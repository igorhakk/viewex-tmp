package app.viewex.core.type

import kotlin.reflect.KClass

class EmptyValueException(
    typeClass: KClass<*>
) : Exception("Class [ ${typeClass.qualifiedName} ] has an empty value")
