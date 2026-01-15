package com.example.project1_group15

/**
 * Curated list of major upcoming astronomical events.
 * This data is manually verified and updated for reliability.
 */
object UpcomingEventsData {

    val events = listOf(

        AstronomyEvent(
            date = "2026-03-29",
            displayDate = "Sun 29 Mar 2026",
            title = "Total Solar Eclipse",
            time = "10:42 UTC",
            description = "A total solar eclipse visible across parts of Europe, Asia, and Africa.",
            type = EventType.SOLAR_ECLIPSE
        ),

        AstronomyEvent(
            date = "2026-08-12",
            displayDate = "Wed 12 Aug 2026",
            title = "Perseid Meteor Shower Peak",
            time = "All Night",
            description = "One of the brightest meteor showers of the year with up to 100 meteors per hour.",
            type = EventType.METEOR_SHOWER
        ),

        AstronomyEvent(
            date = "2026-09-07",
            displayDate = "Mon 07 Sep 2026",
            title = "Total Lunar Eclipse",
            time = "18:12 UTC",
            description = "The Moon will pass completely through Earth's shadow, turning red.",
            type = EventType.LUNAR_ECLIPSE
        ),

        AstronomyEvent(
            date = "2026-12-02",
            displayDate = "Wed 02 Dec 2026",
            title = "Venus at Greatest Brightness",
            time = "After Sunset",
            description = "Venus shines at its brightest in the evening sky.",
            type = EventType.PLANETARY_EVENT
        )
    )
}
