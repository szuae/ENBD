#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_test_enbdtest_Keys_apiKey(JNIEnv *env, jobject object) {
    std::string api_key = "17218098-3d64dfbb28965e7994ce6e69f";
    return env->NewStringUTF(api_key.c_str());
}