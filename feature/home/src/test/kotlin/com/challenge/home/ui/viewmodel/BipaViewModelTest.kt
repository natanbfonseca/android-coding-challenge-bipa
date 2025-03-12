import com.challenge.domain.usecase.GetNodesUseCase
import com.challenge.home.ui.event.BipaEvent
import com.challenge.home.ui.state.BipaState
import com.challenge.home.ui.BipaViewModel
import com.challenge.model.Node
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test

@ExperimentalCoroutinesApi
@ExtendWith(MockitoExtension::class)
class BipaViewModelTest {

    private lateinit var viewModel: BipaViewModel
    private lateinit var getNodesUseCase: GetNodesUseCase
    private val testDispatcher: TestDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getNodesUseCase = mock()
        viewModel = BipaViewModel(getNodesUseCase)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `handleEvent with LoadNodes success`() = runTest(testDispatcher) {
        // Given
        val nodes = listOf(Node("1", "Node 1"), Node("2", "Node 2"))
        whenever(getNodesUseCase()).thenReturn(flowOf(nodes))

        // When
        viewModel.sendEvent(BipaEvent.LoadNodes)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(nodes, viewModel.uiState.value.nodes)
        assertFalse(viewModel.uiState.value.isLoading)
        assertNull(viewModel.uiState.value.error)
    }

    @Test
    fun `handleEvent with LoadNodes success result`() = runTest(testDispatcher) {
        // Given
        val nodes = listOf(Node("1", "Node 1"), Node("2", "Node 2"))
        whenever(getNodesUseCase()).thenReturn(flowOf(nodes))

        // When
        viewModel.sendEvent(BipaEvent.LoadNodes)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(nodes, viewModel.uiState.value.nodes)
        assertFalse(viewModel.uiState.value.isLoading)
        assertNull(viewModel.uiState.value.error)
    }

    @Test
    fun `handleEvent with LoadNodes failure`() = runTest(testDispatcher) {
        // Given
        val errorMessage = "Failed to load nodes"
        whenever(getNodesUseCase()).thenReturn(flow { throw Exception(errorMessage) })

        // When
        viewModel.sendEvent(BipaEvent.LoadNodes)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(errorMessage, viewModel.uiState.value.error)
        assertFalse(viewModel.uiState.value.isLoading)
    }

    @Test
    fun `handleEvent with LoadNodes multiple calls`() = runTest(testDispatcher) {
        // Given
        val nodes1 = listOf(Node("1", "Node 1"))
        val nodes2 = listOf(Node("2", "Node 2"))
        whenever(getNodesUseCase()).thenReturn(flowOf(nodes1), flowOf(nodes2))

        // When
        viewModel.sendEvent(BipaEvent.LoadNodes)
        testDispatcher.scheduler.advanceUntilIdle()
        viewModel.sendEvent(BipaEvent.LoadNodes)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(nodes2, viewModel.uiState.value.nodes)
        assertFalse(viewModel.uiState.value.isLoading)
        assertNull(viewModel.uiState.value.error)
    }

    @Test
    fun `loadNodes getNodesUseCase`() = runTest(testDispatcher) {
        // Given
        val errorMessage = "getNodesUseCase exception"
        val exception = Exception(errorMessage)
        whenever(getNodesUseCase()).thenReturn(flow { throw exception })

        // When
        viewModel.sendEvent(BipaEvent.LoadNodes)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(errorMessage, viewModel.uiState.value.error)
        assertFalse(viewModel.uiState.value.isLoading)
    }

    @Test
    fun `loadNodes empty nodes`() = runTest(testDispatcher) {
        // Given
        whenever(getNodesUseCase()).thenReturn(flowOf(emptyList()))

        // When
        viewModel.sendEvent(BipaEvent.LoadNodes)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(viewModel.uiState.value.nodes.isEmpty())
        assertFalse(viewModel.uiState.value.isLoading)
        assertNull(viewModel.uiState.value.error)
    }

    @Test
    fun `loadNodes large node list`() = runTest(testDispatcher) {
        // Given
        val largeNodeList = List(1000) { Node(it.toString(), "Node $it") }
        whenever(getNodesUseCase()).thenReturn(flowOf(largeNodeList))

        // When
        viewModel.sendEvent(BipaEvent.LoadNodes)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(largeNodeList, viewModel.uiState.value.nodes)
        assertFalse(viewModel.uiState.value.isLoading)
        assertNull(viewModel.uiState.value.error)
    }

    @Test
    fun `loadNodes ui state not changed`() = runTest(testDispatcher) {
        // Given
        val initialUiState = viewModel.uiState.value
        whenever(getNodesUseCase()).thenReturn(flow { emit(emptyList()) })

        // When
        viewModel.sendEvent(BipaEvent.LoadNodes)
        testDispatcher.scheduler.advanceUntilIdle()

        //Then
        verify(getNodesUseCase)()
        assertEquals(initialUiState, viewModel.uiState.value)
        assertEquals(BipaState(isLoading = false), viewModel.uiState.value)
    }
}