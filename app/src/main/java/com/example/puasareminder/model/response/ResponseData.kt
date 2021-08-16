package id.co.booksapp.model.response

data class ResponseData<T> (
    val value: Int,
    val status: String,
    val message: String,
    val data: T
)