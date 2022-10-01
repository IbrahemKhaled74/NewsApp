package domain.models


data class SourcesItemDTO(

    val country: String? = null,

    val name: String? = null,

    val description: String? = null,

    val language: String? = null,

    val id: String? = null,

    val category: String? = null,

    val url: String? = null
)

data class SourceResponseDTO(

    val sources: List<SourcesItemDTO?>? = null,

    val status: String? = null,

    val code: String? = null,

    val message: String? = null

)
