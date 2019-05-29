package com.example.pik.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.Context
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pik.form.ChangePasswordForm
import com.example.pik.CredentialsStore.password
import com.example.pik.R
import com.example.pik.REST.Repository
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*
import java.lang.ref.WeakReference

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SettingsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SettingsFragment : Fragment() {

    /**
     * Keep track of the registration task to ensure we can cancel it if requested.
     */
    private var mChangePasswordTask: ChangePasswordTask? = null

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        view.change_password_button.setOnClickListener {
            attemptRegister()
        }
        return view
    }

    private fun attemptRegister() {
        if (mChangePasswordTask != null) {
            return
        }

        // Reset errors.
        old_password.error = null
        new_password.error = null
        new_password_repeat.error = null

        // Store values at the time of the login attempt.
        val oldPassword = old_password.text.toString()
        val newPassword = new_password.text.toString()
        val newPasswordRepeat = new_password_repeat.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.// Show a progress spinner, and kick off a background task to
        // perform the user login attempt.
        // There was an error; don't attempt login and focus the first
        // form field with an error.


        if (TextUtils.isEmpty(oldPassword)) {
            old_password.error = getString(R.string.error_field_required)
            focusView = old_password
            cancel = true
        }
        if (TextUtils.isEmpty(newPassword)) {
            new_password.error = getString(R.string.error_field_required)
            focusView = new_password
            cancel = true
        }
        if (TextUtils.isEmpty(newPasswordRepeat)) {
            new_password_repeat.error = getString(R.string.error_field_required)
            focusView = new_password_repeat
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
            val changePasswordForm =
                ChangePasswordForm(oldPassword, newPassword, newPasswordRepeat)
            mChangePasswordTask = ChangePasswordTask(changePasswordForm, context!!)
            mChangePasswordTask!!.execute(null as Void?)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            change_password_form.visibility = if (show) View.GONE else View.VISIBLE
            change_password_form.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 0 else 1).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        change_password_form.visibility = if (show) View.GONE else View.VISIBLE
                    }
                })

            changePasswordProgress.visibility = if (show) View.VISIBLE else View.GONE
            changePasswordProgress.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 1 else 0).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        changePasswordProgress.visibility = if (show) View.VISIBLE else View.GONE
                    }
                })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            changePasswordProgress.visibility = if (show) View.VISIBLE else View.GONE
            change_password_form.visibility = if (show) View.GONE else View.VISIBLE
        }
    }

    fun passwordChangeFail() {
        Toast.makeText(context!!, "Couldn't change password", Toast.LENGTH_LONG).show()
    }

    fun passwordChangeSuccess() {
        Toast.makeText(context!!, "Password changed", Toast.LENGTH_LONG).show()
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    inner class ChangePasswordTask internal constructor(changePasswordForm: ChangePasswordForm, context: Context) :
        AsyncTask<Void, Void, Boolean>() {

        private val context: WeakReference<Context> = WeakReference(context)
        private val changePasswordForm: WeakReference<ChangePasswordForm> = WeakReference(changePasswordForm)

        override fun doInBackground(vararg params: Void): Boolean? {
            val repository = Repository(context.get()!!)
            return repository.changePassword(changePasswordForm.get()!!)
        }

        override fun onPostExecute(success: Boolean?) {
            mChangePasswordTask = null
            showProgress(false)

            if (success!!) {
                password = changePasswordForm.get()!!.newPassword
                passwordChangeSuccess()
            } else {
                passwordChangeFail()

            }
        }

        override fun onCancelled() {
            mChangePasswordTask = null
            showProgress(false)
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun passwordChanged()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}
