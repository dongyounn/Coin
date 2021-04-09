//package com.coin.upbit.global.domain
//
//import java.time.LocalDateTime
//import javax.persistence.Column
//import javax.persistence.MappedSuperclass
//import javax.persistence.PrePersist
//import javax.persistence.PreUpdate
//
//@MappedSuperclass
//abstract class BaseDomain {
//    @Column(name = "CREATED_AT", columnDefinition = "DATE", updatable = false)
//    lateinit var createdAt: LocalDateTime
//
//    @Column(name = "UPDATED_AT", columnDefinition = "DATE")
//    lateinit var updatedAt: LocalDateTime
//
//    @PrePersist
//    fun prePersist() {
//        this.createdAt = LocalDateTime.now()
//        this.updatedAt = LocalDateTime.now()
//    }
//
//    @PreUpdate
//    fun preUpdate() {
//        this.updatedAt = LocalDateTime.now()
//    }
//}