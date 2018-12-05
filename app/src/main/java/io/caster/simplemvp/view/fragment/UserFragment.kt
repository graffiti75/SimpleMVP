package io.caster.simplemvp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.caster.simplemvp.MvpApplication
import io.caster.simplemvp.R
import io.caster.simplemvp.presentation.UserPresenter
import io.caster.simplemvp.view.UserView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import javax.inject.Inject

/**
 * The V in MVP (Model View Presenter)
 */
class UserFragment : Fragment(), UserView {
    companion object {
        private val USER_ID = "user_id"
    }

    @Inject lateinit var userPresenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity!!.application as MvpApplication).component!!.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_main, container, false)
        v.user_save.setOnClickListener { userPresenter!!.saveUser() }
        return v
    }

    override fun onResume() {
        super.onResume()
        userPresenter!!.setView(this)
    }

    override fun getUserId(): Int {
        return if (arguments == null) 0 else arguments!!.getInt(USER_ID, 0)
    }

    override fun displayFirstName(name: String) {
        user_first_name.setText(name)
    }

    override fun displayLastName(name: String) {
        user_last_name.setText(name)
    }

    override fun showUserNotFoundMessage() {
        Toast.makeText(activity, R.string.user_not_found, Toast.LENGTH_LONG).show()
    }

    override fun showUserSavedMessage() {
        Toast.makeText(activity, R.string.user_saved, Toast.LENGTH_SHORT).show()
    }

    override fun getFirstName(): String {
        return user_first_name.text.toString()
    }

    override fun getLastName(): String {
        return user_last_name.text.toString()
    }

    override fun showUserNameIsRequired() {
        Toast.makeText(activity, R.string.user_name_required_message, Toast.LENGTH_SHORT).show()
    }
}