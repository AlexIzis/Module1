package com.example.module1

import android.util.Log
import com.google.crypto.tink.*
import com.google.crypto.tink.aead.AeadConfig
import java.nio.charset.Charset

class Tink {

    fun example() {
        AeadConfig.register()
        val keySetHandle: KeysetHandle = KeysetHandle.generateNew(
            KeyTemplates.get("AES128_GCM")
        )
        val aead: Aead = keySetHandle.getPrimitive(Aead::class.java)
        val plaintext = "qwerty"
        val aad = "wasd"

        val encryptedText = aead.encrypt(plaintext.toByteArray(), aad.toByteArray())
        Log.d("tink", encryptedText.toString())
        val decryptedText = aead.decrypt(encryptedText, aad.toByteArray())
        Log.d("tink", decryptedText.toString(Charset.defaultCharset()))
    }

    fun test() {
        var plaintext = "qwerty"
        val contextInfo = ""
        AeadConfig.register()
        val privateKeySetHandle: KeysetHandle = KeysetHandle.generateNew(
            KeyTemplates.get("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM")
        )
        val publicKeySetHandle = privateKeySetHandle.publicKeysetHandle
        val hybridEncrypt = publicKeySetHandle.getPrimitive(HybridEncrypt::class.java)
        val ciphertext = hybridEncrypt.encrypt(plaintext.toByteArray(), contextInfo.toByteArray())

        val hybridDecrypt = privateKeySetHandle.getPrimitive(HybridDecrypt::class.java)
        plaintext = hybridDecrypt.decrypt(ciphertext, contextInfo.toByteArray())
            .toString(Charset.defaultCharset())
    }
}