package com.test.enbdtest

public object Keys {
    init {
        System.loadLibrary("native-lib")
    }

    external fun apiKey(): String
}
