package com.example.panzehir.models

import java.io.Serializable

class User : Serializable {
    lateinit var email : String
    lateinit var token : String
    lateinit var firstNamePatient : String
    lateinit var lastNamePatient : String
    lateinit var bloodPatient : String
    lateinit var idPatient : String
    lateinit var weightPatient : String
    lateinit var heightPatient : String
    lateinit var genderPatient : String
    lateinit var addressPatient : String

    lateinit var firstNamePatientRelative : String
    lateinit var lastNamePatientRelative : String
    lateinit var degreePatientRelative : String
    lateinit var phone1PatientRelative : String
    lateinit var phone2PatientRelative : String
    lateinit var password : String
    lateinit var passwordAccount : String


}