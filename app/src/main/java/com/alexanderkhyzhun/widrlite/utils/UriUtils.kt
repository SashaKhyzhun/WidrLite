package com.alexanderkhyzhun.widrlite.utils

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract


/**
 * @author Alexander Khyzhun
 * Created on 17 June, 2019
 */

fun Context.retrieveContactNumber(uriContact: Uri): String {
    var contactID = empty()
    var contactNumber: String? = null

    val cursorID = contentResolver.query(uriContact,
        arrayOf(ContactsContract.Contacts._ID), null, null, null
    )
    if (cursorID.moveToFirst()) {
        contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID))
    }
    cursorID.close()

    val cursorPhone = contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,

        arrayOf(contactID), null
    )

    if (cursorPhone.moveToFirst()) {
        contactNumber = cursorPhone.getString(
            cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
        )
    }
    cursorPhone.close()
    return contactNumber ?: empty()
}

fun Context.retrieveContactName(uriContact: Uri): String {
    var contactName: String = empty()
    val cursor = contentResolver.query(
        uriContact, null, null, null, null
    )
    if (cursor.moveToFirst()) {
        contactName = cursor.getString(
            cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
        )
    }

    cursor.close()
    return contactName
}