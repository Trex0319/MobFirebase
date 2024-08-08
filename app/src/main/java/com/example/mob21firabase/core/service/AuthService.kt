package com.example.mob21firabase.core.service

import android.content.Context
import com.example.mob21firabase.data.model.User
import com.example.mob21firabase.data.repo.UserRepo
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

class AuthService(
//    private val userRepo: UserRepo,
    private val context: Context
){
    private val auth =  FirebaseAuth.getInstance()

    suspend fun loginWithEmailAndPass(email: String, pass: String): FirebaseUser? {
        val res = auth.signInWithEmailAndPassword(email, pass).await()
        return res.user
    }

    suspend fun createUserWithEmailAndPass(email: String, pass: String): Boolean {
        val res = auth.createUserWithEmailAndPassword(email, pass).await()
        return res.user != null
    }

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun logout() {
        auth.signOut()
    }

    fun getCurrentUser(): FirebaseUser?  {
        return auth.currentUser
    }

    fun getUid(): String? {
        return auth.currentUser?.uid
    }

    suspend fun signInWithGoogle(credential: GoogleIdTokenCredential) {
        val firebaseCredential = GoogleAuthProvider.getCredential(credential.idToken, null)
        auth.signInWithCredential(firebaseCredential).await()
//        userRepo.createUser(
//            User(credential.displayName?: "", "",  "")
//        )
    }
}