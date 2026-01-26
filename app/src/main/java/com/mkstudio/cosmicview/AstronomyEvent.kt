package com.mkstudio.cosmicview

/**
 * Represents a major astronomical event shown in the calendar.
 * Data is curated and updated manually for reliability.
 */
data class AstronomyEvent(
    val date: String,          // e.g. "2026-03-29"
    val displayDate: String,   // e.g. "Sun 29 Mar 2026"
    val title: String,         // e.g. "Total Solar Eclipse"
    val time: String,          // e.g. "10:42 UTC"
    val description: String,  // Short readable explanation
    val type: EventType
)

enum class EventType {
    SOLAR_ECLIPSE,
    LUNAR_ECLIPSE,
    PLANETARY_EVENT,
    METEOR_SHOWER
}
