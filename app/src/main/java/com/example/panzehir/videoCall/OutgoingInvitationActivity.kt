package com.example.panzehir.videoCall

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.panzehir.R
import com.example.panzehir.databinding.ActivityOutgoingInvitationBinding
import com.example.panzehir.model.User
import com.example.panzehir.network.ApiClient
import com.example.panzehir.network.ApiService
import com.example.panzehir.utilities.Constants
import com.example.panzehir.utilities.PreferenceManager
import com.google.firebase.messaging.FirebaseMessaging
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL
import java.util.*

class OutgoingInvitationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOutgoingInvitationBinding

    private lateinit var preferenceManager: PreferenceManager
    private var inviterToken: String? = null
    private var meetingRoom: String? = null
    private var meetingType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutgoingInvitationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(applicationContext)

        // Show icon
        meetingType = intent.getStringExtra("type")
        if (meetingType != null) {
            if (meetingType == "video") {
                binding.imageMeetingTypeOutgoing.setImageResource(R.drawable.ic_video)
            } else {
                binding.imageMeetingTypeOutgoing.setImageResource(R.drawable.ic_audio)
            }
        }

        val user: User = intent.getSerializableExtra("user") as User
        binding.textFirstCharOutgoing.text = user.firstNamePatient.substring(0, 1)
        binding.textUsernameOutgoing.text = String.format("%s %s ", user.firstNamePatient, user.lastNamePatient)
        binding.textEmailOutgoing.text = user.email

        binding.imageStopInvivation.setOnClickListener { cancelInvitation(user.token) }

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful && task.result != null) {
                inviterToken = task.result
                if (meetingType != null) {
                    initiateMeeting(meetingType!!, user.token)
                }
            }
        }

    }

    private fun initiateMeeting(meetingType: String, receiverToken: String) {
        try {
            val tokens = JSONArray()
            tokens.put(receiverToken)

            val body = JSONObject()
            val data = JSONObject()

            data.put(Constants.REMOTE_MSG_TYPE, Constants.REMOTE_MSG_INVITATION)
            data.put(Constants.REMOTE_MSG_MEETING_TYPE, meetingType)
            data.put(Constants.KEY_FIRST_NAME_PATIENT, preferenceManager.getString(Constants.KEY_FIRST_NAME_PATIENT))
            data.put(Constants.KEY_LAST_NAME_PATIENT, preferenceManager.getString(Constants.KEY_LAST_NAME_PATIENT))
            data.put(Constants.KEY_EMAIL, preferenceManager.getString(Constants.KEY_EMAIL))
            data.put(Constants.REMOTE_MSG_INVITER_TOKEN, inviterToken)
            data.put(Constants.KEY_ID_PATIENT, preferenceManager.getString(Constants.KEY_ID_PATIENT))

            meetingRoom =
                preferenceManager.getString(Constants.KEY_ID_PATIENT) + "_" + UUID.randomUUID().toString().substring(0, 5)
            data.put(Constants.REMOTE_MSG_MEETING_ROOM, meetingRoom)

            body.put(Constants.REMOTE_MSG_DATA, data)
            body.put(Constants.REMOTE_MSG_REGISTRATION_IDS, tokens)

            sendRemoteMessage(body.toString(), Constants.REMOTE_MSG_INVITATION)

        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun sendRemoteMessage(remoteMessageBody: String, type: String) {
        val apiClient = ApiClient().getClient()
        apiClient!!.create(ApiService::class.java).sendRemoteMessage(
            Constants.getRemoteMessageHeaders(), remoteMessageBody
        ).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    if (type == Constants.REMOTE_MSG_INVITATION) {
                        Toast.makeText(this@OutgoingInvitationActivity, "Arama başarıyla gönderildi", Toast.LENGTH_SHORT).show()
                    } else if (type == Constants.REMOTE_MSG_INVITATION_RESPONSE) {
                        Toast.makeText(this@OutgoingInvitationActivity, "Arama iptal edildi", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    }
                } else {
                    Toast.makeText(this@OutgoingInvitationActivity, response.message(), Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@OutgoingInvitationActivity, t.message, Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }

    private fun cancelInvitation(receiverToken: String) {
        try {
            val tokens = JSONArray()
            tokens.put(receiverToken)

            val body = JSONObject()
            val data = JSONObject()

            data.put(Constants.REMOTE_MSG_TYPE, Constants.REMOTE_MSG_INVITATION_RESPONSE)
            data.put(Constants.REMOTE_MSG_INVITATION_RESPONSE, Constants.REMOTE_MSG_INVITATION_CANCELLED)

            body.put(Constants.REMOTE_MSG_DATA, data)
            body.put(Constants.REMOTE_MSG_REGISTRATION_IDS, tokens)

            sendRemoteMessage(body.toString(), Constants.REMOTE_MSG_INVITATION_RESPONSE)

        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private val invitationResponseReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val type: String? = intent.getStringExtra(Constants.REMOTE_MSG_INVITATION_RESPONSE)
            type?.let {
                if (type == Constants.REMOTE_MSG_INVITATION_ACCEPTED) {
                    //Jitsi Meet connect
                    try {
                        val serverURL = URL("https://meet.jit.si")
                        val builder = JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverURL)
                            .setWelcomePageEnabled(false)
                            .setRoom(meetingRoom)
                        if (meetingType == "audio") {
                            builder.setVideoMuted(true)
                        }
                        JitsiMeetActivity.launch(this@OutgoingInvitationActivity, builder.build())
                        finish()
                    } catch (e: Exception) {
                        Toast.makeText(this@OutgoingInvitationActivity, e.message, Toast.LENGTH_SHORT).show()
                        finish()
                    }
                } else if (type == Constants.REMOTE_MSG_INVITATION_REJECTED) {
                    Toast.makeText(context, "Arama reddedildi", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(Constants.REMOTE_MSG_INVITATION_RESPONSE)
        LocalBroadcastManager.getInstance(applicationContext).registerReceiver(invitationResponseReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(applicationContext).unregisterReceiver(invitationResponseReceiver)
    }


}