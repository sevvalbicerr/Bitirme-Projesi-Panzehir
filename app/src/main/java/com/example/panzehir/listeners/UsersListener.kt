package com.example.panzehir.listeners

import com.example.panzehir.model.User

interface UsersListener {

    fun initiateVideoMeeting(user : User)

    fun initiateAudioMeeting(user: User)
}