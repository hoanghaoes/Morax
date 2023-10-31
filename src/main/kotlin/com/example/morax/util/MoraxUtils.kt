package com.example.morax.util

import com.fasterxml.uuid.Generators

class MoraxUtils {
    companion object {
        fun newUUID(): String {
            return Generators.timeBasedGenerator().generate().toString()
        }
    }
}