package com.mkstudio.cosmicview

data class NasaImageSearchResponse(
    val collection: NasaImageCollection
)

data class NasaImageCollection(
    val items: List<NasaImageItem>
)

data class NasaImageItem(
    val links: List<NasaImageLink>?
)

data class NasaImageLink(
    val href: String,
    val rel: String,
    val render: String?
)
