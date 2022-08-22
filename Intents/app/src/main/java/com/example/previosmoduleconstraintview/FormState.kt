package com.example.previosmoduleconstraintview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FormState(
    val valid: Boolean
) : Parcelable {

    fun valid(): FormState {
        return copy(valid = false)
    }

    fun invalid(): FormState {
        return copy(valid = true)
    }


}