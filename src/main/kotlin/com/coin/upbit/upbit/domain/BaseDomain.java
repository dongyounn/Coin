package com.coin.upbit.upbit.domain;

import com.sun.istack.NotNull;
import kotlin.jvm.internal.Intrinsics;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseDomain {
   @Column(
      name = "CREATED_AT",
      updatable = false
   )
   @NotNull
   public LocalDateTime createdAt;
   @Column(
      name = "UPDATED_AT")
   @NotNull
   public LocalDateTime updatedAt;

   @PrePersist
   public final void prePersist() {
      LocalDateTime var10001 = LocalDateTime.now();
      Intrinsics.checkNotNullExpressionValue(var10001, "LocalDateTime.now()");
      this.createdAt = var10001;
      var10001 = LocalDateTime.now();
      Intrinsics.checkNotNullExpressionValue(var10001, "LocalDateTime.now()");
      this.updatedAt = var10001;
   }

   @PreUpdate
   public final void preUpdate() {
      LocalDateTime var10001 = LocalDateTime.now();
      Intrinsics.checkNotNullExpressionValue(var10001, "LocalDateTime.now()");
      this.updatedAt = var10001;
   }
}