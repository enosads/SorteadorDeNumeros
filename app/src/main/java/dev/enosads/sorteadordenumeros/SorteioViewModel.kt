package dev.enosads.sorteadordenumeros

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

data class UiState(
    val drawAmountNumber: Int = 2,
    val initialLimit: Int = 1,
    val finalLimit: Int = 100,
    val shouldntRepeatNumbers: Boolean = false,
    val currentDrawNumber: Int = 0,
    val drawNumbers: List<Int> = emptyList()

)

class SorteioViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun setDrawAmountNumber(drawAmountNumber: Int) {
        _uiState.value = _uiState.value.copy(drawAmountNumber = drawAmountNumber)
    }

    fun setInitialLimit(initialLimit: Int) {
        _uiState.value = _uiState.value.copy(initialLimit = initialLimit)
    }

    fun setFinalLimit(finalLimit: Int) {
        _uiState.value = _uiState.value.copy(finalLimit = finalLimit)
    }

    fun setShouldntRepeatNumbers(shouldRepeatNumbers: Boolean) {
        _uiState.value = _uiState.value.copy(shouldntRepeatNumbers = shouldRepeatNumbers)
    }

    fun drawNumbers() {
        val drawNumbers = mutableListOf<Int>()
        repeat(_uiState.value.drawAmountNumber) {
            var number = Random.nextInt(_uiState.value.initialLimit, _uiState.value.finalLimit + 1)
            while (drawNumbers.contains(number) && _uiState.value.shouldntRepeatNumbers) {
                number = Random.nextInt(_uiState.value.initialLimit, _uiState.value.finalLimit + 1)
            }
            drawNumbers.add(number)
        }
        _uiState.value =
            _uiState.value.copy(
                currentDrawNumber = _uiState.value.currentDrawNumber + 1,
                drawNumbers = drawNumbers.toList()
            )

    }


}