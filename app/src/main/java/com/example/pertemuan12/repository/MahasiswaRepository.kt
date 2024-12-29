package com.example.pertemuan12.repository

import com.example.pertemuan12.model.Mahasiswa
import com.example.pertemuan12.serviceAPI.MahasiswaService
import okio.IOException

interface MahasiswaRepository {
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    suspend fun getMahasiswa(): List<Mahasiswa>


    suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa)

    suspend fun deleteMahasiswa(nim: String)

    suspend fun getMahasiswaByNim(nim: String): Mahasiswa
}

class NetworkKontakRepository(
    private val kontakApiService: MahasiswaService
) : MahasiswaRepository {

    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        kontakApiService.insertMahasiswa(mahasiswa)
    }

    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        kontakApiService.updateMahasiswa(nim, mahasiswa)
    }

    override suspend fun getMahasiswa(): List<Mahasiswa> {
        return kontakApiService.getAllMahasiswa()
    }

    override suspend fun deleteMahasiswa(nim: String) {
        try {
            val reponse = kontakApiService.deleteMahasiswa(nim)
            if (!reponse.isSuccessful) {
                throw IOException("Failed to delete kontak. HTTP Status code: " +
                        "${reponse.code()}")
            } else {
                reponse.message()
                println(reponse.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getMahasiswaByNim(nim: String): Mahasiswa {
        return kontakApiService.getMahasiswabyNim(nim)
    }
}