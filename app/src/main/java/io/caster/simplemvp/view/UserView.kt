package io.caster.simplemvp.view

interface UserView {
    fun getUserId(): Int

    fun displayFirstName(name: String)
    fun displayLastName(name: String)

    fun showUserNotFoundMessage()
    fun showUserSavedMessage()

    fun getFirstName(): String
    fun getLastName(): String

    fun showUserNameIsRequired()
}