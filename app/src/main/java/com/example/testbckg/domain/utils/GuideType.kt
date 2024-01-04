package com.example.testbckg.domain.utils

enum class GuideType {
    MOUNTAIN {
        override fun toString(): String {
            return "Mountain guide"
        }
    },
    CULTURAL {
        override fun toString(): String {
            return "Cultural guide"
        }
    }
}