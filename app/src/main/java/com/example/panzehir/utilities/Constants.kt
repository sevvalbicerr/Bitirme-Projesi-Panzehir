package com.example.panzehir.utilities

object Constants {
    const val KEY_COLLECTION_USERS = "users"
    const val KEY_FCM_TOKEN = "fcm_token"

    const val KEY_FIRST_NAME_PATIENT = "patient_name_first"
    const val KEY_LAST_NAME_PATIENT = "patient_name_last"
    const val KEY_BLOOD_PATIENT = "patient_blood"
    const val KEY_ID_PATIENT = "patient_id"
    const val KEY_BIRTHDAY_PATIENT = "patient_birthday"
    const val KEY_WEIGHT_PATIENT = "patient_1weight"
    const val KEY_HEIGHT_PATIENT = "patient_1height"
    const val KEY_GENDER_PATIENT = "patient_gender"
    const val KEY_ADDRESS_PATIENT = "patient_address"

    const val KEY_FIRST_NAME_RELATIVE_PATIENT = "patient_relative_first_name"
    const val KEY_LAST_NAME_RELATIVE_PATIENT = "patient_relative_last_name"
    const val KEY_DEGREE = "patient_relative_degree"
    const val KEY_EMAIL = "patient_relative_email"
    const val KEY_PASSWORD = "patient_relative_password"
    const val KEY_PASSWORD_ACCOUNT = "patient_relative_password_account"
    const val KEY_PHONE1 = "patient_relative_phone1"
    const val KEY_PHONE2 = "patient_relative_phone2"

    const val KEY_PREFERENCE_NAME = "videoMeetingPreference"
    const val KEY_IS_SIGNED_IN = "isSignedIn"

    private const val REMOTE_MSG_AUTHORIZATION = "Authorization"
    private const val REMOTE_MSG_CONTENT_TYPE = "Content-Type"

    const val REMOTE_MSG_TYPE = "type"
    const val REMOTE_MSG_INVITATION = "invitation"
    const val REMOTE_MSG_MEETING_TYPE = "meetingType"
    const val REMOTE_MSG_INVITER_TOKEN = "inviterToken"
    const val REMOTE_MSG_DATA = "data"
    const val REMOTE_MSG_REGISTRATION_IDS = "registration_ids"

    const val REMOTE_MSG_INVITATION_RESPONSE = "invitationResponse"

    const val REMOTE_MSG_INVITATION_ACCEPTED = "accepted"
    const val REMOTE_MSG_INVITATION_REJECTED = "rejected"
    const val REMOTE_MSG_INVITATION_CANCELLED = "cancelled"

    const val REMOTE_MSG_MEETING_ROOM = "meeting_room"

    fun getRemoteMessageHeaders(): HashMap<String, String> {
        val headers: HashMap<String, String> = HashMap()
        headers[REMOTE_MSG_AUTHORIZATION] = "key=AAAA6DXTpuA:APA91bHSf3HNF2E8OeG-DZ6LTrHlUE2loyLOaEHmVY0l1rvkElN2EAdnAfpLSkOhS3QS_-jBh0XaBOA5hb4KzDWiQ698wIStn4DYG6w2xZ-7AJE4bDCvrkqbxSvpQ90BfTEJ2i6n57tq"
        headers[REMOTE_MSG_CONTENT_TYPE] = "application/json"
        return headers
    }

}