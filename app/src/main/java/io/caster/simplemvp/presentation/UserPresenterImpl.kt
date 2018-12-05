package io.caster.simplemvp.presentation

import io.caster.simplemvp.model.User
import io.caster.simplemvp.repository.UserRepository
import io.caster.simplemvp.view.UserView

class UserPresenterImpl(private val userRepository: UserRepository) : UserPresenter {
    private var view: UserView? = null
    private var u: User? = null

    override fun loadUserDetails() {
        if (view == null) throw ViewNotFoundException()
        val userId = view!!.getUserId()
        u = userRepository.getUser(userId)
        if (u == null) {
            view!!.showUserNotFoundMessage()
        } else {
            view!!.displayFirstName(u!!.firstName!!)
            view!!.displayLastName(u!!.lastName!!)
        }
    }

    override fun setView(view: UserView) {
        this.view = view
        loadUserDetails()
    }

    override fun saveUser() {
        if (u != null) {
//            if (TextUtils.isEmpty(view.getFirstName()) || TextUtils.isEmpty(view.getLastName())) {
            if (view!!.getFirstName() == null || view!!.getFirstName().trim { it <= ' ' } == "" ||
                    view!!.getLastName() == null || view!!.getLastName().trim { it <= ' ' } == "") {
                view!!.showUserNameIsRequired()
            } else {
                u!!.firstName = view!!.getFirstName()
                u!!.lastName = view!!.getLastName()
                userRepository.save(u!!)
                view!!.showUserSavedMessage()
            }

        }
    }
}