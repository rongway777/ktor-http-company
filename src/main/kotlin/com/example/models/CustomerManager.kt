package com.example.models

import com.example.log.AppLog
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

object CustomerManager {
    private val customerStorage = mutableListOf<Customer>()
    private val mutex = Mutex()

    suspend fun add(customer: Customer): Boolean {
        return mutex.withLock {
            val addRst = customerStorage.add(customer.copy())
            AppLog.info("CustomerManager.add(customer), customer: {}, addResult: {}", customer, addRst)
            return@withLock addRst
        }
    }

    suspend fun all(): List<Customer> {
        return mutex.withLock {
            val allList = customerStorage.map { it.copy() }
            AppLog.info("CustomerManager.all(), customers.size: {}", allList.size)
            return@withLock allList
        }
    }

    suspend fun id(id: String): Customer? {
        return mutex.withLock {
            val customer = customerStorage.find { it.id == id }?.copy()
            AppLog.info("CustomerManager.id(), id: {}, customer: {}", id, customer)
            return@withLock customer
        }
    }

    suspend fun isEmpty(): Boolean {
        return mutex.withLock {
            val isEmpty = customerStorage.isEmpty()
            AppLog.info("CustomerManager.isEmpty(), isEmpty: {}", isEmpty)
            return@withLock isEmpty
        }
    }

    suspend fun isNotEmpty(): Boolean {
        return mutex.withLock {
            val isNotEmpty = customerStorage.isNotEmpty()
            AppLog.info("CustomerManager.isEmpty(), isNotEmpty: {}", isNotEmpty)
            return@withLock isNotEmpty
        }
    }

    suspend fun rm(id: String): Boolean {
        return mutex.withLock {
            val rmResult = customerStorage.removeIf { it.id == id }
            AppLog.info("CustomerManager.rm(), rmResult: {}", rmResult)
            return@withLock rmResult
        }
    }
}