package com.rezyfr.foodmarket.core.network.model

import com.google.gson.annotations.SerializedName
import com.rezyfr.foodmarket.core.domain.model.PagingResult
import com.rezyfr.foodmarket.core.network.R

data class PagingResponse<T>(
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("data")
    val data: List<T>?,
    @SerializedName("first_page_url")
    val firstPageUrl: String?,
    @SerializedName("from")
    val from: Int?,
    @SerializedName("last_page")
    val lastPage: Int?,
    @SerializedName("last_page_url")
    val lastPageUrl: String?,
    @SerializedName("next_page_url")
    val nextPageUrl: String?,
) {
    companion object {
        fun <T, R> PagingResponse<T>.mapToPagingResult(mapper: (List<T>) -> List<R>): PagingResult<R> {
            return PagingResult(
                currentPage = this.currentPage ?: 0,
                firstPageUrl = this.firstPageUrl.orEmpty(),
                from = this.from ?: 0,
                lastPage = this.lastPage ?: 0,
                lastPageUrl = this.lastPageUrl.orEmpty(),
                nextPageUrl = this.nextPageUrl.orEmpty(),
                data = mapper(this.data.orEmpty())
            )
        }
    }
}