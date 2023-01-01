package app.viewex.example.auth.domain.user

import app.viewex.domain.Repository
import app.viewex.domain.type.UserId

interface UserRepository : Repository.Modifiable<User, UserId> {
}
