package app.viewex.example

import app.viewex.core.secutity.Principal
import app.viewex.example.auth.domain.user.User

open class UserPrincipal(
    override val id: User.Id,
    override val role: User.Role
) : Principal<User.Id, User.Role>
