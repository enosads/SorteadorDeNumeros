package dev.enosads.sorteadordenumeros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dev.enosads.sorteadordenumeros.databinding.FragmentConfiguracaoDeSorteioBinding

class ConfiguracaoDeSorteioFragment : Fragment() {

    private var _binding: FragmentConfiguracaoDeSorteioBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SorteioViewModel by activityViewModels()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfiguracaoDeSorteioBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            swtNotRepeatNumbers.setOnCheckedChangeListener { _, isChecked ->
                swtNotRepeatNumbers.trackTintList =
                    getColorStateList(
                        requireContext(),
                        if (isChecked) R.color.background_brand else R.color.content_tertiary
                    )
                viewModel.setShouldntRepeatNumbers(isChecked)
            }

            etAmountNumbers.addTextChangedListener { text ->
                viewModel.setDrawAmountNumber(text.toString().toIntOrNull() ?: 0)
            }

            etInitialLimit.addTextChangedListener { text ->
                viewModel.setInitialLimit(text.toString().toIntOrNull() ?: 0)
            }

            etFinalLimit.addTextChangedListener { text ->
                viewModel.setFinalLimit(text.toString().toIntOrNull() ?: 0)
            }
        }
    }
}