package korea.seoul.pickple.data.repository.implementation

import korea.seoul.pickple.data.api.UserAPI
import korea.seoul.pickple.data.repository.interfaces.UserRepository

class UserRepositoryImpl(private val userAPI : UserAPI) : UserRepository {

}