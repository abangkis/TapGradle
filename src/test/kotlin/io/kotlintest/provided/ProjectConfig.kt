package io.kotlintest.provided

import io.kotlintest.AbstractProjectConfig

class ProjectConfig: AbstractProjectConfig() {
    private var started: Long = 0

    override fun beforeAll() {
        started = System.currentTimeMillis()
    }

    override fun afterAll() {
        val time = System.currentTimeMillis() - started
        println("overall time [ms]: $time")
    }

    // if you have multiple core
//    override fun parallelism(): Int = 2
}