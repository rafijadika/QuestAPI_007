package com.example.pertemuan12.ui.viewmodel

import androidx.compose.runtime.mutableStateOf

class InsertModelView(private val mhs: MahasiswaRepsitory): ViewModel() {
    var UiState by mutableStateOf(UiState())
        private set

    fun updateInsertMhsState(insertUiEvent.InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertMhs() {
        viewModelScope.launch {
            try {
                mhs.insertMahasiswa(uiState.insertUiEvent.toMhs())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}

data class InsertUiState(
    var insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    var nim: String = "",
    var nama: String = "",
    var alamat: String = "",
    var jenisKelamin: String = "",
    var kelas: String = "",
    var angkatan: String = ""
)

fun InsertUiEvent.toMhs(): Mahasiswa = Mahasiswa (
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan
)