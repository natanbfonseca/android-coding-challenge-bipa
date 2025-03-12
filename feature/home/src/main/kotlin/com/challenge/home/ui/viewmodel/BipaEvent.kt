package com.challenge.home.ui.viewmodel

sealed interface BipaEvent {
    data object LoadNodes : BipaEvent
}