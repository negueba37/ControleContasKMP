package com.fortunaTeste

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform