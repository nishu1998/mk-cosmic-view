package com.example.project1_group15

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project1_group15.databinding.ActivityUpcomingEventsBinding
import com.example.project1_group15.databinding.ItemAstronomyEventBinding

class UpcomingEventsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpcomingEventsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpcomingEventsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        renderEvents()
    }

    private fun renderEvents() {

        val groupedEvents = UpcomingEventsData.events.groupBy { it.displayDate }

        groupedEvents.forEach { (date, events) ->

            // 📅 Date Header (simple TextView is fine)
            val dateView = TextView(this).apply {
                text = date
                textSize = 18f
                setTextColor(getColor(android.R.color.white))
                setPadding(0, 24, 0, 12)
            }
            binding.eventsContainer.addView(dateView)

            // 🌑 Event Cards (ViewBinding)
            events.forEach { event ->

                val eventBinding = ItemAstronomyEventBinding.inflate(
                    LayoutInflater.from(this),
                    binding.eventsContainer,
                    false
                )

                // Title & Time
                eventBinding.tvEventTitle.text = event.title
                eventBinding.tvEventTime.text = event.time
                eventBinding.tvEventDescription.text = event.description

                // Icon mapping
                val icon = when (event.type) {
                    EventType.SOLAR_ECLIPSE -> "🌞"
                    EventType.LUNAR_ECLIPSE -> "🌕"
                    EventType.METEOR_SHOWER -> "☄️"
                    EventType.PLANETARY_EVENT -> "🪐"
                }

                eventBinding.tvEventIcon.text = icon


                // Expand / collapse
                var expanded = false

                eventBinding.btnExpand.setOnClickListener {
                    expanded = !expanded

                    eventBinding.tvEventDescription.animate()
                        .alpha(if (expanded) 1f else 0f)
                        .setDuration(200)
                        .withStartAction {
                            if (expanded) {
                                eventBinding.tvEventDescription.visibility = View.VISIBLE
                            }
                        }
                        .withEndAction {
                            if (!expanded) {
                                eventBinding.tvEventDescription.visibility = View.GONE
                            }
                        }
                        .start()

                    eventBinding.btnExpand.animate()
                        .rotation(if (expanded) 180f else 0f)
                        .setDuration(200)
                        .start()

                    eventBinding.root.animate()
                        .translationZ(if (expanded) 12f else 0f)
                        .setDuration(200)
                        .start()
                }


                binding.eventsContainer.addView(eventBinding.root)
            }

        }
    }
}
