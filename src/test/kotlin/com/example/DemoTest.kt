package com.example

import io.kotest.core.spec.style.BehaviorSpec
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest5.MicronautKotest5Extension.getMock
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

@MicronautTest
class DemoTest1(private val someService: SomeService) : BehaviorSpec() {

    @MockBean(MyRepo::class)
    val mockRepo = mockk<MyRepo>()

    init {
        Given("the repo is mocked") {
            every { mockRepo.save(any<MyEntity>()) } returns MyEntity(name = "bob")

            When("we invoke the service") {
                someService.addName("some-name")

                Then("the mockRepo was invoked") {
                    verify { mockRepo.save(MyEntity(name = "some-name")) }
                }
            }
        }
    }
}

@MicronautTest
class DemoTest2(private val someService: SomeService, repo: MyRepo) : BehaviorSpec({
    Given("the repo is mocked") {
        val mockRepo = getMock(repo)
        every { mockRepo.save(any<MyEntity>()) } returns MyEntity(name = "bob")

        When("we invoke the service") {
            someService.addName("some-name")

            Then("the mockRepo was invoked") {
                verify { mockRepo.save(MyEntity(name = "some-name")) }
            }
        }
    }
}) {
    @MockBean(MyRepo::class)
    val mockRepo = mockk<MyRepo>()
}
