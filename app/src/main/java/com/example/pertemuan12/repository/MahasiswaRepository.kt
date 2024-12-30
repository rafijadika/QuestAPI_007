package com.example.pertemuan12.repository

import com.example.pertemuan12.model.Mahasiswa
import com.example.pertemuan12.serviceAPI.MahasiswaService


interface MahasiswaRepository {
    suspend fun getMahasiswa(): List<Mahasiswa>
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)
    suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa)
    suspend fun deleteMahasiswa(nim: String)
    suspend fun getMahasiswaByNim(nim: String): Mahasiswa
}

class NetworkMahasiswaRepository(private val mahasiswaService: MahasiswaService)
    : MahasiswaRepository
{
    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        mahasiswaService.insertMahasiswa(mahasiswa)
    }

    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        mahasiswaService.updateMahasiswa(nim, mahasiswa)
    }

    override suspend fun deleteMahasiswa(nim: String) {
        try {
            val response = mahasiswaService.deleteMahasiswa(nim)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete mahasiswa. HTTP Status Code: ${response.code()}")
            }
            else{
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getMahasiswa(): List<Mahasiswa> = mahasiswaService.getAllMahasiswa()
    override suspend fun getMahasiswaByNim(nim: String): Mahasiswa {
        return mahasiswaService.getMahasiswabyNim(nim)
    }
}
