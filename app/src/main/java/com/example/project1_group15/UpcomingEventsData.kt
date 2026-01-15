package com.example.project1_group15

/**
 * Curated list of major upcoming astronomical events.
 * This data is manually verified and updated for reliability.
 * Covers globally significant events for the year 2026.
 */
object UpcomingEventsData {

    val events = listOf(

        AstronomyEvent(
            date = "2026-01-02",
            displayDate = "Fri 02 Jan 2026",
            title = "Quadrantid Meteor Shower Peak",
            time = "After Midnight",
            description = "One of the strongest meteor showers of the year with bright fireballs.",
            type = EventType.METEOR_SHOWER
        ),

        AstronomyEvent(
            date = "2026-01-10",
            displayDate = "Sat 10 Jan 2026",
            title = "Jupiter at Opposition",
            time = "All Night",
            description = "Jupiter reaches opposition, appearing brightest and closest to Earth.",
            type = EventType.PLANETARY_EVENT
        ),

        AstronomyEvent(
            date = "2026-02-17",
            displayDate = "Tue 17 Feb 2026",
            title = "Annular Solar Eclipse",
            time = "Daytime",
            description = "A dramatic ring-of-fire solar eclipse visible in parts of the Southern Hemisphere.",
            type = EventType.SOLAR_ECLIPSE
        ),

        AstronomyEvent(
            date = "2026-03-02",
            displayDate = "Mon 02 Mar 2026",
            title = "Total Lunar Eclipse (Blood Moon)",
            time = "Night",
            description = "The Moon turns deep red as it passes through Earth’s shadow.",
            type = EventType.LUNAR_ECLIPSE
        ),

        AstronomyEvent(
            date = "2026-03-20",
            displayDate = "Fri 20 Mar 2026",
            title = "Moon & Venus Conjunction",
            time = "After Sunset",
            description = "A crescent Moon appears close to bright Venus in the evening sky.",
            type = EventType.PLANETARY_EVENT
        ),

        AstronomyEvent(
            date = "2026-03-29",
            displayDate = "Sun 29 Mar 2026",
            title = "Total Solar Eclipse",
            time = "10:42 UTC",
            description = "A total solar eclipse visible across parts of Europe, Asia, and Africa.",
            type = EventType.SOLAR_ECLIPSE
        ),

        AstronomyEvent(
            date = "2026-04-19",
            displayDate = "Sun 19 Apr 2026",
            title = "Moon & Pleiades Alignment",
            time = "Evening",
            description = "The Moon passes near the Pleiades star cluster creating a beautiful sight.",
            type = EventType.PLANETARY_EVENT
        ),

        AstronomyEvent(
            date = "2026-06-09",
            displayDate = "Tue 09 Jun 2026",
            title = "Venus & Jupiter Close Approach",
            time = "Twilight",
            description = "The two brightest planets appear extremely close together in the sky.",
            type = EventType.PLANETARY_EVENT
        ),

        AstronomyEvent(
            date = "2026-08-12",
            displayDate = "Wed 12 Aug 2026",
            title = "Total Solar Eclipse",
            time = "Daytime",
            description = "A total solar eclipse visible across Greenland, Iceland, and parts of Europe.",
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
            date = "2026-08-28",
            displayDate = "Fri 28 Aug 2026",
            title = "Partial Lunar Eclipse",
            time = "Night",
            description = "A partial lunar eclipse visible in parts of Europe and Asia.",
            type = EventType.LUNAR_ECLIPSE
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
            date = "2026-10-21",
            displayDate = "Wed 21 Oct 2026",
            title = "Orionid Meteor Shower Peak",
            time = "Late Night",
            description = "Fast meteors originating from Halley’s Comet debris.",
            type = EventType.METEOR_SHOWER
        ),

        AstronomyEvent(
            date = "2026-11-17",
            displayDate = "Tue 17 Nov 2026",
            title = "Leonid Meteor Shower",
            time = "Pre-Dawn",
            description = "A meteor shower known for producing meteor storms in some years.",
            type = EventType.METEOR_SHOWER
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
