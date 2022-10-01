package domain.models


data class ArticlesItemDTO(

    val publishedAt: String? = null,

    val author: String? = null,

    val urlToImage: String? = null,

    val description: String? = null,

    val source: SourceDTO? = null,

    val title: String? = null,

    val url: String? = null,

    val content: String? = null
)

data class NewsResponseDTO(

    val totalResults: Int? = null,

    val articles: MutableList<ArticlesItemDTO?>? = null,

    val status: String? = null
)

data class SourceDTO(

    val name: String? = null,

    val id: String? = null
)
