package com.example.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory


object AppLog {

    private val logger: Logger = LoggerFactory.getLogger(AppLog::class.java)

    fun info(msg: String, vararg args: Any?) {
        logger.info(msg, *args)
    }

}