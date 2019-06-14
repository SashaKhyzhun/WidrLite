package com.alexanderkhyzhun.widrlite.utils

import com.alexanderkhyzhun.widrlite.data.models.response.RPMessage
import com.alexanderkhyzhun.widrlite.data.models.response.RPNotification

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
fun generateRPNotifications() = listOf(
    RPNotification(
        "0", "a", "1", "1", "https://p.djinni.co/4d/725b004e2411e99e0cd35f598479ff/200.jpg", "Alex Nikiforov", "is Looking for an osteopath in Paris", "12 jan", false),
    RPNotification(
        "1", "a", "1", "1", "https://p.djinni.co/af/783bb0568d11e8b71765c89b1d2a6d/200.jpg", "Anastasia Kuchinska", "accepted your mutual contacts request", "12 jan", false),
    RPNotification(
        "2", "a", "1", "1", "https://djinni.co/static/i/default-userpic@2x.png", "Valentine Bosenko", "accepted your mutual contacts request", "12 jan", false),
    RPNotification(
        "3", "a", "1", "1", "https://p.djinni.co/35/5853915fe14274beb368841c83a96c/200.jpg", "Alina Morozenko", "accepted your mutual contacts request", "12 jan", true),
    RPNotification(
        "4", "a", "1", "1", "https://p.djinni.co/af/783bb0568d11e8b71765c89b1d2a6d/200.jpg", "Elena Kryz", "is looking for an osteopath in Paris", "12 jan", false),
    RPNotification(
        "5", "a", "1", "1", "https://djinni.co/static/i/default-userpic@2x.png", "Aliona Shvechikova", "accepted your mutual contacts request", "12 jan", false),
    RPNotification(
        "6", "a", "1", "1", "https://p.djinni.co/d2/c05d30b1b111e89c0cbdb280f735ba/200.jpg", "Roksolana Kushliyak", "accepted your mutual contacts request", "12 jan", false),
    RPNotification(
        "7", "a", "1", "1", "https://p.djinni.co/50/8cafdb14c54649bfefe746c056a886/200.jpg", "Aleksandr Yanivskyy", "accepted your mutual contacts request", "12 jan", true),
    RPNotification(
        "8", "a", "1", "1", "https://p.djinni.co/82/edcd693a1b4f2790091b5c1fe7e22c/200.jpg", "Aliona Meleshko", "accepted your mutual contacts request", "12 jan", false),
    RPNotification(
        "9", "a", "1", "1", "https://p.djinni.co/c9/cb4ef0792711e89d7ef96e2b7389c3/200.jpg", "Rostyslav Amykalo", "accepted your mutual contacts request", "12 jan", false),
    RPNotification(
        "10", "a", "1", "1", "https://p.djinni.co/r/983340dd/50.jpg", "Inna Pogorelaya", "accepted your mutual contacts request", "12 jan", false),
    RPNotification(
        "11", "a", "1", "1", "https://p.djinni.co/4d/725b004e2411e99e0cd35f598479ff/200.jpg", "Alina Koval", "accepted your mutual contacts request", "12 jan", false)
)


fun generateRPMessages() = listOf(
    RPMessage("1",
        "https://p.djinni.co/1f/c1856a65504c4889dcd2df6d1b4bd3/200.jpg",
        "Yana Mandziuk", "Kyiv", "Recruiter",
        "Often said by the winner in poker, as the others 'weep' over the loss.",
        "11:24 AM", "12", true),

    RPMessage("1",
        "https://p.djinni.co/3a/2679f71b8e420786e3bbe9d14b75b7/200.jpg",
        "Jess Davie", "Algiers", "Manager",
        "Someone who isn't witty or sharp, but rather, they are ignorant, unintelligent, or senseless.",
        "12 Jan", "0", false),

    RPMessage("1",
        "https://p.djinni.co/46/5861903dab11e9a551f174f589ba88/200.jpg",
        "Iman Cornish", "Tokyo", "Taxi driver",
        "Someone that calls for help when it is not needed. Someone who is lying.",
        "12 Jan", "0", false),

    RPMessage("1",
        "https://p.djinni.co/1f/c1856a65504c4889dcd2df6d1b4bd3/200.jpg",
        "Kenan Bradford", "Turin", "Designer",
        "High quality, exceptional; something that's very valuable.",
        "12 Jan", "0", false),

    RPMessage("1",
        "https://p.djinni.co/69bbce6d51/200.jpg",
        "Bushra Andersen", "Incheon", "CTO",
        "To tolerate or endure through the unexpected mishappenings you may encounter from time to time.",
        "12 Jan", "0", false),

    RPMessage("1",
        "https://djinni.co/static/i/default-userpic@2x.png",
        "Jardel Humphrey", "Havana", "Developer",
        "Things that are fixed with great speed, but as a result, it's probably not going to work very well.",
        "21 Jan", "0", false),

    RPMessage("1",
        "https://p.djinni.co/6e/6236302d2911e9baf017c7e67cff32/200.jpg",
        "Alyce Lynch", "Paris", "Babysitter",
        "A situation that has gotten way more serious or interesting due to recent complexities or developments.",
        "11 Apr", "0", false),

    RPMessage("1",
        "https://p.djinni.co/95ba9011c2/200.jpg",
        "Lillie Ashley", "Amsterdam", "Recruiter",
        "Having confidence in a specific outcome; being almost sure about something.",
        "22 May", "0", false)
    )