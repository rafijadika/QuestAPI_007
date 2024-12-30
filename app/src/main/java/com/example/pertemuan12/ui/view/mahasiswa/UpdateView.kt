package com.example.pertemuan12.ui.view.mahasiswa

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pertemuan12.ui.costumwidget.CostumeTopAppBar
import com.example.pertemuan12.ui.viewmodel.PenyediaViewModel
import com.example.pertemuan12.ui.viewmodel.UpdateViewModel
import com.example.praktikum_pertemuan12.ui.navigation.DestinasiNavigasi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdate: DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Update Mhs"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdateViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdate.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(12.dp)) {
            // Tambahkan teks "Update Data Mahasiswa"
            Text(
                text = "Update Data Mahasiswa",
                fontSize = MaterialTheme.typography.titleLarge.fontSize * 1.2f, // Ukuran lebih besar
                fontWeight = MaterialTheme.typography.titleLarge.fontWeight, // Tebal sesuai tema
                color = MaterialTheme.colorScheme.onSurface, // Warna sesuai tema (hitam pada default)
                modifier = Modifier.padding(bottom = 16.dp)
            )

            EntryBody(
                insertUiState = viewModel.updateUiState,
                onSiswaValueChange = viewModel::updateInsertMhsState,
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.updateMhs()
                        delay(600)
                        withContext(Dispatchers.Main) {
                            onNavigate()
                        }
                    }
                }
            )
        }
    }
}
