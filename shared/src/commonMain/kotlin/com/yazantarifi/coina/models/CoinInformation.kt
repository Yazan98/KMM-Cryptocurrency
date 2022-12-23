package com.yazantarifi.coina.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinInformation(
    @SerialName("id") val id: String? = "",
    @SerialName("symbol") val symbol: String? = "",
    @SerialName("name") val name: String? = "",
    @SerialName("hashing_algorithm") val hashingAlgorithm: String? = "",
    @SerialName("block_time_in_minutes") val blockTimeInMinutes: String? = "",
    @SerialName("categories") val categories: ArrayList<String>? = arrayListOf(),
    @SerialName("description") val description: CoinDescription? = null,
    @SerialName("links") val links: CoinLinks? = null,
    @SerialName("image") val image: CoinIcon? = null,
    @SerialName("country_origin") val countryOrigin: String? = "",
    @SerialName("genesis_date") val genesisDate: String? = "",
    @SerialName("sentiment_votes_up_percentage") val votesUpPercentage: Double? = 0.0,
    @SerialName("sentiment_votes_down_percentage") val votesDownPercentage: Double? = 0.0,
    @SerialName("developer_score") val developerScore: Double? = 0.0,
    @SerialName("community_score") val communityScore: Double? = 0.0,
    @SerialName("liquidity_score") val liquidityScore: Double? = 0.0,
    @SerialName("public_interest_score") val publicInterestScore: Double? = 0.0,
    @SerialName("market_cap_rank") val marketCapRank: Int? = 0,
    @SerialName("community_data") val communityData: CoinCommunity? = null,
    @SerialName("developer_data") val developerData: CoinDeveloper? = null,
    @SerialName("market_data") val marketData: CoinMarketPriceInfo? = null,
)

@Serializable
data class CoinDescription(
    @SerialName("en") val value: String? = ""
)

@Serializable
data class CoinMarketPriceInfo(
    @SerialName("sparkline_7d") val value: CoinMarketPrice? = null
)

@Serializable
data class CoinMarketPrice(
    @SerialName("price") val value: ArrayList<Double>? = null
)

@Serializable
data class CoinLinks(
    @SerialName("homepage") val links: ArrayList<String>? = null,
    @SerialName("blockchain_site") val blockchainSites: ArrayList<String>? = null,
    @SerialName("official_forum_url") val officialForumUrls: ArrayList<String>? = null,
    @SerialName("announcement_url") val announcementUrls: ArrayList<String>? = null,
    @SerialName("twitter_screen_name") val twitterUsername: String? = "",
    @SerialName("facebook_username") val facebookUsername: String? = "",
    @SerialName("telegram_channel_identifier") val telegramUsername: String? = "",
    @SerialName("subreddit_url") val redditUsername: String? = "",
    @SerialName("repos_url") val repositoriesUrls: CoinRepository? = null,
)

@Serializable
data class CoinRepository(
    @SerialName("github") val githubLinks: ArrayList<String>? = null,
    @SerialName("bitbucket") val bitbucketLinks: ArrayList<String>? = null,
)

@Serializable
data class CoinIcon(
    @SerialName("thumb") val thumb: String? = null,
    @SerialName("small") val small: String? = null,
    @SerialName("large") val large: String? = null,
)

@Serializable
data class CoinCommunity(
    @SerialName("facebook_likes") val facebookLikes: Long? = 0L,
    @SerialName("twitter_followers") val twitterFollowers: Long? = 0L,
    @SerialName("reddit_subscribers") val redditSubscribers: Long? = 0L,
    @SerialName("reddit_average_posts_48h") val redditAveragePosts: Double? = 0.0,
    @SerialName("reddit_average_comments_48h") val redditAverageComments: Double? = 0.0,
)

@Serializable
data class CoinDeveloper(
    @SerialName("forks") val forks: Long? = 0L,
    @SerialName("stars") val stars: Long? = 0L,
    @SerialName("subscribers") val subscribers: Long? = 0L,
    @SerialName("total_issues") val totalIssues: Long? = 0L,
    @SerialName("closed_issues") val closedIssues: Long? = 0L,
    @SerialName("pull_requests_merged") val pullRequestsMerged: Long? = 0L,
    @SerialName("pull_request_contributors") val contributors: Long? = 0L,
)