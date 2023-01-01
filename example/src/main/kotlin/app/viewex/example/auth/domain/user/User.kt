package app.viewex.example.auth.domain.user

import app.viewex.core.secutity.Principal
import app.viewex.core.type.BooleanType
import app.viewex.core.type.StringType
import app.viewex.domain.Entity
import app.viewex.domain.EntityId
import app.viewex.domain.type.Email
import app.viewex.domain.type.FullName
import app.viewex.domain.type.PhoneNumber

class User(
    var firstName: FullName.NamePart,
    var middleName: FullName.NamePart,
    var lastName: FullName.NamePart,
    var phoneNumber: PhoneNumber.External,
    var email: Email,
    var userRole: Role,
    var enabled: BooleanType,
    id: Id,
) : Entity.Identified<User.Id>(id) {


//    companion object : ModifiedEntity<User, User.Id>(User::class, AuthGroup) {
//
//        override fun getIdMapper(): ValueMapper<User.Id> = ValueMapper {
//            Id.parse(it)
//        }
//    }

    class Id private constructor(
        uuid: Any?
    ) : EntityId.AsUuid(uuid), Principal.Id {
        companion object {
            fun parse(uuid: Any): Id = Id(uuid)
        }
    }

    class Role(
        roleName: Any
    ) : StringType(roleName, 3, 10, Principal.Role.Pattern), Principal.Role
}
