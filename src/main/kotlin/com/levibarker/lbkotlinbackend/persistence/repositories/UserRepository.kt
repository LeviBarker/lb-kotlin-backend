package com.levibarker.lbkotlinbackend.persistence.repositories

import com.levibarker.lbkotlinbackend.persistence.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long>