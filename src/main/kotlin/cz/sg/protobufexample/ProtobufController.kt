package cz.sg.protobufexample

import cz.sg.protobuf.ProtobufTraining.Course
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProtobufController(private val courseRepository: CourseRepository) {

    @GetMapping("/courses/{id}")
    fun customer(@PathVariable id: Int): Course? = courseRepository.courses[id]
}