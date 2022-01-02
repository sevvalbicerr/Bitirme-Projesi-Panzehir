package com.example.panzehir.listeners

import com.example.panzehir.models.User

interface UsersListener {

    fun initiateVideoMeeting(user : User)

    fun initiateAudioMeeting(user: User)
}