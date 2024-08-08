package com.example.mob21firabase.core.di

import com.example.mob21firabase.core.service.AuthService
import com.example.mob21firabase.data.repo.TodoRepo
import com.example.mob21firabase.data.repo.TodoRepoFirebase
import com.example.mob21firabase.data.repo.TodoRepoReel
import com.example.mob21firabase.data.repo.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun provideTodoRepo(authService: AuthService): TodoRepo {
        return TodoRepoFirebase(authService)
    }

    @Provides
    @Singleton
    fun provideUserRepo(): UserRepo {
        return UserRepo()
    }
}