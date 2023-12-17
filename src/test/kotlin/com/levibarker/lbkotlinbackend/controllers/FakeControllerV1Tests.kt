package com.levibarker.lbkotlinbackend.controllers
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.levibarker.lbkotlinbackend.models.User
import com.levibarker.lbkotlinbackend.services.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class FakeControllerV1Test @Autowired constructor(private val mockMvc: MockMvc) {

    @MockBean
    private lateinit var userService: UserService

    private val objectMapper = jacksonObjectMapper()

    @BeforeEach
    fun setup() {
        // Mock userService.generateSome method
        `when`(userService.generateSome(Mockito.anyInt())).thenReturn(listOf())
    }

    @Test
    fun `getOkResponse Should Return OK Status`() {
        mockMvc.perform(get("/v1/fake/ok"))
            .andExpect(status().isOk)
    }

    @Test
    fun `getBadRequestResponse Should Return Bad Request Status`() {
        mockMvc.perform(get("/v1/fake/bad-request"))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `getInternalServerError Should Return Internal Server Error Status`() {
        mockMvc.perform(get("/v1/fake/internal-server-error"))
            .andExpect(status().isInternalServerError)
    }

    @Test
    fun `getUsers Should Return OK Status and User Data`() {
        `when`(userService.generateSome(Mockito.anyInt())).thenReturn((1..5).map {
            User()
        })
        val response = mockMvc.perform(get("/v1/fake/users?count=5").contentType(MediaType.APPLICATION_JSON))
        response.andExpect(status().isOk)
    }
}