package dev.propoc.moneysaver

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform