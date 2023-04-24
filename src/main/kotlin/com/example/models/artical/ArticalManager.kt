package com.example.models.artical

import com.example.log.AppLog
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger

object ArticalManager {

    private val articleStorage = mutableListOf<Article>()
    private val idCounter = AtomicInteger()
    private val mutex = Mutex()

    suspend fun all(): List<Article> {
        return mutex.withLock {
            val allList = articleStorage.map { it.copy() }
            AppLog.info("ArticalManager.all(), articales.size: {}", allList.size)
            return@withLock allList
        }
    }

    suspend fun add(article: Article): Int {
        return mutex.withLock {
            articleStorage.add(article.copy())
            AppLog.info(
                "ArticalManager.add(artical), id {}, title: {}, artical-size: {}",
                article.id,
                article.title,
                articleStorage.size
            )
            return@withLock article.id
        }
    }

    suspend fun id(id: Int): Article? {
        return mutex.withLock {
            val artical = articleStorage.find { it.id == id }?.copy()
            AppLog.info("ArticalManager.id(), id: {}, artical.title: {}", id, artical)
            return@withLock artical
        }
    }

    fun createArtical(title: String, body: String): Article = Article(
        idCounter.addAndGet(1),
        title,
        body
    )

    suspend fun addTestInfo(): Int {
        add(createArtical("title1", "body1"))
        add(createArtical("title2", "body2"))
        add(createArtical("title3", "body3"))
        return articleStorage.size
    }
}