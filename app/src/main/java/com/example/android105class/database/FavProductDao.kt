package com.example.android105class.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favProductInfo: List<FavProductEntity>)

    @Query("SELECT * FROM FavProduct WHERE id= :id LIMIT 1")
    suspend fun getProductsById(id: Int): FavProductEntity?

    @Delete
    suspend fun delete(favorite: FavProductEntity)

    @Query("SELECT * FROM FavProduct")
    fun getAllFavorites(): LiveData<List<FavProductEntity>>
}