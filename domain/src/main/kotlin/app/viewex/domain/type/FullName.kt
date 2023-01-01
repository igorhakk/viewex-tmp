package app.viewex.domain.type

import app.viewex.core.type.EmptyValueException
import app.viewex.core.type.StringType
import app.viewex.core.type.ValueType
import kotlin.reflect.KClass

open class FullName(
    val firstName: NamePart,
    val middleName: NamePart,
    val lastName: NamePart
) : ValueType.String {

    companion object {
        val Empty = FullName(NamePart.Empty, NamePart.Empty, NamePart.Empty)

        fun parse(fullName: Any?): FullName {
            return fullName.toString().split(" ").filter { it.isNotBlank() }.let { split ->

                val middleName = split.getOrNull(2)?.let {
                    val middle = split.getOrNull(1)
                    if (middle == "null" || middle == null) return@let NamePart.Empty
                    NamePart.parse(middle)
                } ?: NamePart.Empty

                val lastName = split.getOrNull(2)?.let { last ->
                    NamePart.parse(last)
                } ?: NamePart.parseOrEmpty(split.getOrNull(1))

                FullName(NamePart.parseOrEmpty(split.getOrNull(0)), middleName, lastName)
            }
        }
    }

    init {
        if (!(firstName.isEmpty && lastName.isEmpty && middleName.isEmpty)) {
            if ((firstName.isEmpty && !lastName.isEmpty) || (!firstName.isEmpty && lastName.isEmpty))
                throw IllegalArgumentException("First name and Last name must not be empty")
        }
    }

    override val isEmpty: Boolean = firstName.isEmpty && lastName.isEmpty && middleName.isEmpty

    override val rawType: KClass<String> = String::class

    override val value: String get() = validValue ?: throw EmptyValueException(FullName::class)

    override fun toString(): String = validValue ?: "empty"

    private val validValue: String?
        get() {
            if (isEmpty) return null
            return let {
                if (middleName.isEmpty) return@let ""
                middleName.value.plus(" ")
            }.let { "${firstName.value} $it${lastName.value}" }
        }

    class NamePart private constructor(
        name: Any?
    ) : StringType(name, 1, 25, "[А-Яа-я\\w\\s]{1,25}") {

        companion object {

            val Empty = NamePart(null)

            fun parse(name: Any) :NamePart = NamePart(name)

            fun parseOrEmpty(name: Any?): NamePart = parseOrNull(name) ?: Empty

            fun parseOrNull(name: Any?): NamePart? = name?.let { parse(it) }

        }
    }

}
