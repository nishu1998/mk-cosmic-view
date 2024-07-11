package com.example.project1_group15

data class NeoWsResponse(
    val near_earth_objects: Map<String, List<NeoWs>>
)

data class NeoWs(
    val id: String,
    val name: String,
    val absolute_magnitude_h: Double,
    val estimated_diameter: EstimatedDiameter,
    val is_potentially_hazardous_asteroid: Boolean
)

data class EstimatedDiameter(
    val kilometers: Diameter
)

data class Diameter(
    val estimated_diameter_min: Double,
    val estimated_diameter_max: Double
)
