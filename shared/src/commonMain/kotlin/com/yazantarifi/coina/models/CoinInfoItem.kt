package com.yazantarifi.coina.models

import com.yazantarifi.coina.formatDecimalSeparator
import kotlin.jvm.JvmField

data class CoinInfoItem(
    val type: Int,
    val title: String,
    val value: String,
    val image: String? = null,
    val chart: ArrayList<Double>? = null,
    val lastUpdated: String? = null,
) {
    companion object {

        @JvmField val TYPE_TITLE = 1
        @JvmField val TYPE_CHART = 2
        @JvmField val TYPE_SECTION_TITLE = 3
        @JvmField val TYPE_SECTION = 4
        @JvmField val TYPE_DESCRIPTION = 5


        fun getScreenItems(coinInformation: CoinInformation?): ArrayList<CoinInfoItem> {
            val items = ArrayList<CoinInfoItem>()

            coinInformation?.let {
                items.add(CoinInfoItem(TYPE_TITLE, coinInformation.name ?: "", coinInformation.symbol ?: "", coinInformation.image?.large))

                items.add(CoinInfoItem(TYPE_SECTION_TITLE, "Price Changes", "", ""))
                items.add(CoinInfoItem(TYPE_CHART, coinInformation.name ?: "", coinInformation.symbol ?: "", coinInformation.image?.large, coinInformation.marketData?.value?.value, coinInformation.marketData?.value?.lastUpdated))

                items.add(CoinInfoItem(TYPE_SECTION_TITLE, "Description", "", ""))
                items.add(CoinInfoItem(TYPE_DESCRIPTION, "", coinInformation.description?.value ?: "", ""))

                items.add(CoinInfoItem(TYPE_SECTION_TITLE, "Overview", "", ""))
                items.add(CoinInfoItem(TYPE_SECTION, "Current Price", coinInformation.marketData?.value?.value?.last()?.formatDecimalSeparator() ?: ""))
                items.add(CoinInfoItem(TYPE_SECTION, "Rank", coinInformation.marketCapRank?.toString() ?: ""))
                items.add(CoinInfoItem(TYPE_SECTION, "Market Cap", coinInformation.marketCapRank?.formatDecimalSeparator() ?: ""))
                items.add(CoinInfoItem(TYPE_SECTION, "Hashing Algorithm", coinInformation.hashingAlgorithm ?: ""))
                items.add(CoinInfoItem(TYPE_SECTION, "Facebook Likes", coinInformation.communityData?.facebookLikes?.formatDecimalSeparator() ?: ""))
                items.add(CoinInfoItem(TYPE_SECTION, "Twitter Followers", coinInformation.communityData?.twitterFollowers?.formatDecimalSeparator() ?: ""))
                items.add(CoinInfoItem(TYPE_SECTION, "Reddit Subscribers", coinInformation.communityData?.redditSubscribers?.formatDecimalSeparator() ?: ""))
                items.add(CoinInfoItem(TYPE_SECTION, "Git Forks", coinInformation.developerData?.forks?.formatDecimalSeparator() ?: ""))
                items.add(CoinInfoItem(TYPE_SECTION, "Git Stars", coinInformation.developerData?.stars?.formatDecimalSeparator() ?: ""))
                items.add(CoinInfoItem(TYPE_SECTION, "Git Subscribers", coinInformation.developerData?.subscribers?.formatDecimalSeparator() ?: ""))
                items.add(CoinInfoItem(TYPE_SECTION, "Git Total Issues", coinInformation.developerData?.totalIssues?.formatDecimalSeparator() ?: ""))
                if (coinInformation.categories?.isNotEmpty() == true) {
                    items.add(CoinInfoItem(TYPE_SECTION, "Category", coinInformation.categories?.get(0) ?: ""))
                }

                if (coinInformation.countryOrigin?.isNotEmpty() == true) {
                    items.add(CoinInfoItem(TYPE_SECTION, "Country", coinInformation.countryOrigin ?: ""))
                }
            }

            return items
        }
    }
}
