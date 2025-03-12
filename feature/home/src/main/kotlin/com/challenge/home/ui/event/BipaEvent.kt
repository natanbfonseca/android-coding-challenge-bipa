package com.challenge.home.ui.event

sealed interface BipaEvent {
    data object LoadNodes : BipaEvent
}